/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class ParametreController implements Initializable {

    @FXML
    private StackPane screen;
    @FXML
    private AnchorPane id_menu;
    @FXML
    private Label personnel;
    @FXML
    private Label fonction;
    @FXML
    private Label configuration;
    @FXML
    private Label utilisateur;
    @FXML
    private Label indice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
