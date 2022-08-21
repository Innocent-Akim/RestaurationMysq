/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lib.app.App;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class ProduitsController implements Initializable {

    @FXML
    private AnchorPane produit_interface;
    @FXML
    private AnchorPane itemList;
    @FXML
    private JFXListView<?> itemProduit;
    @FXML
    private TextField recher_facture;
    @FXML
    private JFXButton btn_add_produit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_add_produit.setOnAction((action) -> {
            try {
                App.popOverMenu(btn_add_produit, getClass().getResource("/lib/gui/AddProduit.fxml"),
                        PopOver.ArrowLocation.TOP_CENTER);
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
            }
        });
    }

}
