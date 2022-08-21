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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class PopOperationController implements Initializable {

    @FXML
    private Label b_fournir;
    @FXML
    private Label b_nouveau;
    @FXML
    private Label b_ventes;
    @FXML
    private Label b_caisse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Calloperation(MouseEvent event) {
    }
    
}
