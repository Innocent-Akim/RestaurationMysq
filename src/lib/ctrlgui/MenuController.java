/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lib.Main.Acces;
import static lib.Main.Main.stage;
import lib.Main.View;
import lib.app.Datasource;
import lib.app.Files;
import lib.app.Vars;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class MenuController implements Initializable {

    @FXML
    private JFXButton btn_facturation;
    @FXML
    private JFXButton btn_settings;
    @FXML
    private AnchorPane screenMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initEvent();
        Files.CreadFolder(null);
        Datasource.refEntreprise = Vars.vars.getRefEntreprise();
    }

    void initEvent() {
        screenMenu.setOnMouseEntered((event) -> {
            initAcces();
        });
        btn_facturation.setOnAction((action) -> {
            stage.setContent(View.instance().get(View.V_FACTURE));

        });
        btn_settings.setOnAction((action) -> {
            stage.setContent(View.instance().get(View.PRINCIPARE));
        });
    }

    void initAcces() {
        Acces.setAcces(btn_settings, "TABLEAU DE BORD");
        Acces.setAcces(btn_facturation, "Facturation");

    }
}
