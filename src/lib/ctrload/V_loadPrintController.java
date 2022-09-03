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

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class V_loadPrintController implements Initializable {
    
    @FXML
    private JFXButton btn_annuler;
    @FXML
    private Label designation;
    @FXML
    private Label items;
    @FXML
    private Label qte;
    public static String designationString, itemsString, qtString, codeString;
    @FXML
    private Label code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        designation.setText(designationString);
        qte.setText(qtString);
        items.setText(itemsString);
        code.setText(codeString);
        
    }    
    
}
