/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lib.Main.View;
import lib.app.App;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class ParametreController implements Initializable {

    @FXML
    private StackPane screen;
    @FXML
    private AnchorPane id_menu;
    @FXML
    private Label personnel;

    @FXML
    private Label indice;
    public static StackPane contains;
    @FXML
    private Label taux;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        contains = screen;
        indice.setLayoutY(90);
        initEvent();
    }

    void initEvent() {
        View.instance().setContaint(screen, View.PERSONNE);
        App.getInstance().IsSeleted(personnel, taux);
        personnel.setOnMouseClicked((Action) -> {
            indice.setLayoutY(90);
            App.getInstance().IsSeleted(personnel, taux);
            View.instance().setContaint(screen, View.PERSONNE);
        });
        taux.setOnMouseClicked((Action) -> {
            indice.setLayoutY(130);
            View.instance().setContaint(screen, View.TAUX);
            App.getInstance().IsSeleted(taux, personnel);
        });

    }

    public void ecran_remove(StackPane pane, String ecran) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ecran));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
