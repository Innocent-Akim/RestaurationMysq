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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lib.app.App;
import lib.ctrlgui.UtilisateurController;
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

    public static String nomString, telephoneString, adresseString, prefixNameString, codeIdeString, lbl_titleString;

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
                    App.popOverMenu(card, getClass().getResource("/lib/gui/utilisateur.fxml"),
                            PopOver.ArrowLocation.TOP_CENTER);
                } catch (IOException ex) {
                    System.out.print(ex.getMessage());
                }
            });
        }

    }

}
