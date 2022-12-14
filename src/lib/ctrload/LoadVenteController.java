/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class LoadVenteController implements Initializable {
    
    @FXML
    private AnchorPane data_grid;
    @FXML
    private Label id;
    @FXML
    private Label designation;
    @FXML
    private Label ptotal;
    @FXML
    private FontAwesomeIconView btl_modifier;
    @FXML
    private FontAwesomeIconView btn_annProduit;
    @FXML
    private TextField pu;
    @FXML
    private Label idFacture;
    @FXML
    private TextField qte;
    public static String designationString, qteString, ptString, puString, idFactureString, idString;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        designation.setText(designationString);
        qte.setText(qteString);
        pu.setText(puString);
        ptotal.setText(ptString);
        idFacture.setText(idFactureString);
        id.setText(idString);
        // TODO
    }
    
    @FXML
    private void retireProduitFac(MouseEvent event) {
    }
    
}
