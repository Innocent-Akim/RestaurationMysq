/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
