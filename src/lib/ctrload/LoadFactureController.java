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
public class LoadFactureController implements Initializable {

    @FXML
    private AnchorPane data_grid;
    @FXML
    private Label id;
    @FXML
    private Label designation;
    @FXML
    private TextField tfd_qteFacture;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void retireProduitFac(MouseEvent event) {
    }

}
