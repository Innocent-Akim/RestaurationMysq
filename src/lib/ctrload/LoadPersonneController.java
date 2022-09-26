/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import javafx.scene.layout.AnchorPane;
import lib.app.App;
import lib.ctrlgui.UtilisateurController;
import static lib.dbconnect.Dbconnect.cnx;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class LoadPersonneController implements Initializable {

    @FXML
    private AnchorPane card;
    @FXML
    private Label nom;
    @FXML
    private Label telephone;
    @FXML
    private Label adresse;
    @FXML
    private JFXButton modifier;
    @FXML
    private Label prefixName;
    @FXML
    private Label codeIde;
    @FXML
    private Label lbl_title;
    @FXML
    private JFXCheckBox activeUser;
    @FXML
    private Label index;
    @FXML
    private Label iduser;

    public static String nomString, telephoneString, adresseString, prefixNameString, codeIdeString, lbl_titleString, iduserString;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setText(nomString.trim());
        telephone.setText(telephoneString.trim());
        adresse.setText(adresseString.trim());
        codeIde.setText(codeIdeString.trim());
        prefixName.setText(nomString.substring(0, 1));
        activeUser.setVisible(false);
        iduser.setText(iduserString);
        lbl_title.setText(lbl_titleString.trim());
        if (lbl_title.getText().equals("AGENTS")) {
            card.setOnMouseEntered((action) -> {
                activeUser.setVisible(true);
            });
            card.setOnMouseExited((action) -> {
                activeUser.setVisible(false);
            });
            card.setOnMouseClicked((action) -> {
                try {
                    UtilisateurController.nomString = nom.getText();
                    UtilisateurController.idUser = iduser.getText();
                    App.popOverMenu(card, getClass().getResource("/lib/gui/utilisateur.fxml"),
                            PopOver.ArrowLocation.TOP_CENTER);
                    initTaches();
                } catch (IOException ex) {
                    System.out.print(ex.getMessage());
                }
            });
        }

    }

    public void initTaches() {
        try {
            String query = "SELECT * FROM Tache,Users WHERE Tache.codeUser=Users.code AND Tache.codeUser='" + iduser.getText() + "'";
            ResultSet result = cnx().createStatement().executeQuery(query);
            UtilisateurController.public_listTache.getItems().clear();
            while (result.next()) {
                LoadTacheController.public_code = result.getString("codeUser");
                LoadTacheController.taskpublc = result.getString("designation");
                LoadTacheController.public_etat = result.getString("status");
                UtilisateurController.public_listTache.getItems().add(FXMLLoader.load(getClass().getResource("/lib/load/loadTache.fxml")));
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }

    }

}
