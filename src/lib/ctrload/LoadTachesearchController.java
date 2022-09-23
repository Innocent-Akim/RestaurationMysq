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

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class LoadTachesearchController implements Initializable {

    @FXML
    private AnchorPane cardeTache;
    @FXML
    private Label lblTache;
    public static Label public_tache_string;
    public static String public_String;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblTache.setText(public_String);
        public_tache_string = lblTache;
    }

}
