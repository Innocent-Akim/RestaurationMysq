/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lib.app.Alerte;
import lib.app.Datasource;
import lib.app.Msg;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class TauxController implements Initializable {

    @FXML
    private JFXListView<?> datatype;
    @FXML
    private TextField FldTaux;
    @FXML
    private JFXButton btn_ajouter;
    @FXML
    private JFXListView<?> datatype1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_ajouter.setOnAction((key) -> {
            try {
                if (!FldTaux.getText().trim().equals("")) {
                    boolean status = false;
                    String exist = Datasource.getValue("SELECT status FROM taux WHERE taux='" + FldTaux.getText() + "'");
                    if (exist != null) {
                        status = Datasource.execute("UPDATE taux SET status=? WHERE taux=?", "1".equals(exist) ? "1" : "0", FldTaux.getText());
                        if (status) {
                            Alerte.alerteInformation("Information", Msg.MESSAGE_SAVE);
                            FldTaux.clear();
                        }
                    } else {
                        status = Datasource.execute("INSERT INTO taux SET taux=?", FldTaux.getText());
                        if (status) {
                            Alerte.alerteInformation("Information", Msg.MESSAGE_SAVE);
                            FldTaux.clear();
                        }
                    }
                } else {
                    Alerte.alerteErreur("ERROR", Msg.MESSAGE_ISMPTY);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

}
