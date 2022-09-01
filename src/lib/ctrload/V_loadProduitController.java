/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

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
public class V_loadProduitController implements Initializable {

    @FXML
    private Label designation;
    @FXML
    private Label pu;
    @FXML
    private Label categorie;
    @FXML
    private Label etat;
    @FXML
    private Label id;
    public static String designationString, puString, categorieString, etatString, idString;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        designation.setText(designationString);
        pu.setText(puString);
        categorie.setText(categorieString);
        etat.setText(etatString);
        id.setText(idString);
    }

}
