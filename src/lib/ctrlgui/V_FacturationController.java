/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class V_FacturationController implements Initializable {

    @FXML
    private JFXListView<?> ListClient;
    @FXML
    private JFXListView<?> ListCagorie;
    @FXML
    private JFXListView<?> ListProduit;
    @FXML
    private JFXListView<?> ListFacture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}