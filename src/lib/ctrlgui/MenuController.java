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
import static lib.Main.Main.stage;
import lib.Main.View;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class MenuController implements Initializable {

    @FXML
    private JFXButton btn_facturation;
    @FXML
    private JFXButton btn_rapport;
    @FXML
    private JFXButton btn_settings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initEvent();
    }

    void initEvent() {
        btn_facturation.setOnAction((action) -> {
            stage.setContent(View.instance().get(View.V_FACTURE));

        });
    }
}
