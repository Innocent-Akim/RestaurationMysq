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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lib.Main.Acces;
import lib.ctrload.LoadTachesearchController;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Acces().dataTache(listUtilisateur);
        nom.setText(nomString);
        codeUser.setText(idUser);
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
    }

    @FXML
    private void showListSousCategorie(MouseEvent event) {
    }

}
