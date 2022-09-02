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
import javafx.scene.layout.AnchorPane;
import lib.app.Datasource;
import lib.app.Neurohub;
import lib.app.Vars;
import lib.ctrlgui.V_FacturationController;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class V_loadTableController implements Initializable {

    @FXML
    private Label id;
    @FXML
    private Label nameClient;
    public static String idString, nameClientString;
    @FXML
    private AnchorPane cardTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setText(idString);
        nameClient.setText(nameClientString);
        cardTable.setOnMouseClicked((action) -> {
            V_FacturationController.nameroLabel.setText(Neurohub.neurohub.createFacture(nameClient.getText()));
        });
        // TODO
    }


}
