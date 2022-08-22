/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lib.app.Alerte;
import lib.app.App;
import lib.app.Datasource;
import lib.app.Files;
import lib.app.Msg;
import lib.app.Neurohub;
import lib.ctrload.LoadPersonneController;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class PersonneController implements Initializable {

    @FXML
    private JFXListView<?> listView;
    @FXML
    private JFXButton client;
    @FXML
    private JFXButton agent;
    @FXML
    private TextField nom;
    @FXML
    private TextField contact;
    @FXML
    private TextField adresse;
    @FXML
    private JFXButton btaction;
    @FXML
    private Label idUpdate;
    @FXML
    private Text etiquette;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        App.getInstance().SelectDataFor(agent, client);
        etiquette.setText("Agents");
        initData();
        agent.setOnAction((action) -> {
            App.getInstance().SelectDataFor(agent, client);
            etiquette.setText("Agents");
            initData();
        });
        client.setOnAction((action) -> {
            App.getInstance().SelectDataFor(client, agent);
            etiquette.setText("Clients");
            initData();
        });
        btaction.setOnAction((action) -> {
            if (Neurohub.isFieldsempty(nom, contact, adresse)) {
                Alerte.alerteAvertissement("Attention", Msg.MESSAGE_ISMPTY);
            } else {
                boolean status = Datasource.execute("INSERT INTO personne SET `nom`=?,`contact`=?,`adresse`=?,`type`=?,`refEntreprise`=? ", nom.getText(), contact.getText(), adresse.getText(), etiquette.getText(), Datasource.refEntreprise);
                if (status) {
                    Neurohub.initFields(nom, contact, adresse);
                    initData();
                    Alerte.alerteInformation("Information", Msg.MESSAGE_SAVE);
                }
            }
        });

    }

    void initData() {
        try {
            Datasource.cleanList(listView);
            ResultSet rs = Datasource.getrResultat("SELECT * FROM personne WHERE type='" + etiquette.getText().trim() + "' AND refEntreprise='" + Datasource.refEntreprise + "'");
            while (rs.next()) {
                LoadPersonneController.nomString = rs.getString("nom");
                LoadPersonneController.telephoneString = rs.getString("contact");
                LoadPersonneController.adresseString = rs.getString("adresse");
                LoadPersonneController.codeIdeString = rs.getString("code");
                LoadPersonneController.lbl_titleString = rs.getString("type");
                listView.getItems().add(FXMLLoader.load(getClass().getResource("/lib/load/loadPersonne.fxml")));
            }
        } catch (SQLException e) {
            Files.error("==>Error " + e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(PersonneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
