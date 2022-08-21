/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lib.Main.View;
import lib.app.App;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class PopOperationController implements Initializable {

    @FXML
    private Label b_nouveau;
    @FXML
    private Label b_ventes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        b_nouveau.setOnMouseClicked((key) -> {
            try {
                App.getInstance().IsSeleted(b_nouveau, b_ventes);
                View.instance().setContaint(PrincipalController.screenPane, View.PRODUITS);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        b_ventes.setOnMouseClicked((key) -> {
            try {
                App.getInstance().IsSeleted(b_ventes, b_nouveau);
                View.instance().setContaint(PrincipalController.screenPane, View.FACTURATION);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

}
