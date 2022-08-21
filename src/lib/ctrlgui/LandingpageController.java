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
public class LandingpageController implements Initializable {

    @FXML
    private JFXButton connectvous;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initEvent();
    }

    void initEvent() {
        connectvous.setOnAction((e) -> {
            stage.setContent(View.instance().get(View.LOGIN));
        });
    }
}
