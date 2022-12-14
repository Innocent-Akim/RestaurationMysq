/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lib.Main.Acces;
import static lib.Main.Main.stage;
import lib.Main.View;
import lib.app.App;
import lib.app.Vars;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class PrincipalController implements Initializable {

    @FXML
    private StackPane screen;
    @FXML
    private AnchorPane menu_security;
    @FXML
    private AnchorPane pan1;
    @FXML
    private Label b_dash;
    @FXML
    private AnchorPane pan2;
    @FXML
    private Label b_operation;
    @FXML
    private AnchorPane pan4;
    @FXML
    private Label b_rapport;
    @FXML
    private FontAwesomeIconView r;
    @FXML
    private AnchorPane pan41;
    @FXML
    private Label b_parametre;
    @FXML
    private FontAwesomeIconView p;
    @FXML
    private Text txUserName;
    @FXML
    private Text txtFonctionuser;
    public static StackPane screenPane;
    @FXML
    private MaterialDesignIconView btn_deconnection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        screenPane = screen;
        txUserName.setText(Vars.vars.getNom());
        App.getInstance().IsSeleted(b_dash, b_operation, b_rapport, b_parametre);
        View.instance().setContaint(screen, View.DASHBOARD);
        initEvent();
    }

    void initEvent() {
        btn_deconnection.setOnMouseClicked((action) -> {
            stage.setContent(View.instance().get(View.MENU));
        });
        menu_security.setOnMouseEntered((e) -> {
            initAcces();
        });
        b_dash.setOnMouseClicked((event) -> {
            App.getInstance().IsSeleted(b_dash, b_operation, b_rapport, b_parametre);
            View.instance().setContaint(screen, View.DASHBOARD);
        });
        b_operation.setOnMouseClicked((event) -> {
            try {
                App.getInstance().IsSeleted(b_operation, b_dash, b_rapport, b_parametre);
                App.popOverMenu(b_operation, getClass().getResource("/lib/gui/PopOperation.fxml"),
                        PopOver.ArrowLocation.LEFT_CENTER);
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
            }
        });

        b_rapport.setOnMouseClicked((event) -> {
            App.getInstance().IsSeleted(b_rapport, b_operation, b_dash, b_parametre);
            View.instance().setContaint(screen, View.RAPPORT);

        });
        b_parametre.setOnMouseClicked((event) -> {
            App.getInstance().IsSeleted(b_parametre, b_rapport, b_operation, b_dash);
            View.instance().setContaint(screen, View.PARAMETRES);
        });

    }

    void initAcces() {
        Acces.setAcces(b_dash, "Dashboard");
        Acces.setAcces(b_operation, "Operation");
//        Acces.setAcces(, "Comptabilite");
        Acces.setAcces(b_parametre, "Parametre");
        Acces.setAcces(b_rapport, "Rapport");

    }

}
