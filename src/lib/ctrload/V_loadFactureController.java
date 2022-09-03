/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lib.app.App;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class V_loadFactureController implements Initializable {

    @FXML
    private AnchorPane cardFacture;
    @FXML
    private Label id;
    @FXML
    private Label numero;
    @FXML
    private Label montant;
    @FXML
    private Label client;
    public static String idString, numString, montantString, clientString;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setText(idString.trim());
        numero.setText(numString.trim());
        montant.setText(montantString.trim());
        client.setText(clientString.trim());
        cardFacture.setOnMouseClicked((action) -> {
            try {
                App.popOverMenu(cardFacture, getClass().getResource("/lib/uix/v_printFacture.fxml"),
                        PopOver.ArrowLocation.TOP_CENTER);
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
            }
        });
    }

}
