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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lib.Main.Acces;
import lib.app.Alerte;
import lib.app.Datasource;
import lib.app.Msg;
import lib.ctrload.LoadTacheController;
import lib.ctrload.LoadTachesearchController;
import static lib.dbconnect.Dbconnect.cnx;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class UtilisateurController implements Initializable {

    @FXML
    private JFXListView<?> listUtilisateur;
    @FXML
    private JFXButton btn_add;
    @FXML
    private TextField search;
    @FXML
    private Text nom;
    public static String nomString;
    public static String idUser;
    @FXML
    private JFXListView<?> searchList;
    @FXML
    private Label codeUser;
    public static Label codeUserLabel;
    public static TextField public_search;
    public static JFXListView<?> public_listsearch;
    public static JFXListView<?> public_listUtilisateur, public_listTache;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Acces().dataTache(searchList);
        nom.setText(nomString);
        codeUser.setText(idUser);
        codeUserLabel = codeUser;
        public_listsearch = searchList;
        public_listTache = listUtilisateur;
        public_search = search;
        searchList.setVisible(false);
        search.setOnKeyReleased((Action) -> {
            try {
                searchList.getItems().clear();
                searchList.setVisible(true);
                Acces.datadisp.stream().filter((data) -> (data.toUpperCase().contains(search.getText().toUpperCase()))).forEachOrdered((String data) -> {
                    try {
                        LoadTachesearchController.public_String = data;
                        searchList.getItems().add(FXMLLoader.load(getClass().getResource("/lib/load/loadTachesearch.fxml")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        btn_add.setOnAction((action) -> {
            try {
                if (search.getText().equals("")) {
                    Alerte.alerteAvertissement("Attention", Msg.MESSAGE_ISMPTY);
                } else {
                    String exist = Datasource.getValue("SELECT status FROM tache WHERE designation='" + search.getText() + "' AND codeUser='" + codeUser.getText() + "'");
                    if (exist != null) {
                        Datasource.execute("UPDATE `tache` SET  `status`=? WHERE `designation`=? AND `codeUser`=?", "1".equals(exist) ? "0" : "1", search.getText(), codeUser.getText());
                    } else {
                        Datasource.execute("INSERT INTO `tache` SET `designation`=?, `codeUser`=?, `status`=?", search.getText(), codeUser.getText(), "1");
                    }
                    initTaches();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        searchList.setOnMouseExited((action) -> {
            searchList.setVisible(false);
        });
    }

    public void initTaches() {
        try {
            String query = "SELECT * FROM Tache,Users WHERE Tache.codeUser=Users.code AND Tache.codeUser='" + codeUser.getText() + "'";
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

    @FXML
    private void showListSousCategorie(MouseEvent event) {
    }

}
