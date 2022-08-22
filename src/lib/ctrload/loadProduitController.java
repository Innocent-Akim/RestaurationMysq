/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class loadProduitController implements Initializable {

    @FXML
    private TextField designationFld;
    @FXML
    private TextField quantiteFld;
    @FXML
    private TextField categorieFld;
    @FXML
    private Label codeBarLbl;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXButton btn_modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
