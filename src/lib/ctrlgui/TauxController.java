/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lib.app.Alerte;
import lib.app.Datasource;
import lib.app.Msg;
import lib.ctrload.V_loadTauxController;

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
    public static JFXListView<?> public_datatype;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        public_datatype = datatype;
        init();
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
                init();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    void init() {
        try {
            Datasource.cleanList(public_datatype);
            ResultSet rs = Datasource.getrResultat("SELECT taux,(SELECT designation FROM devise WHERE status=1) devise,status FROM taux");
            while (rs.next()) {
                V_loadTauxController.deviseString = rs.getString("devise");
                V_loadTauxController.tauxString = rs.getString("taux");
                V_loadTauxController.indexString = rs.getString("status");
                public_datatype.getItems().add(FXMLLoader.load(getClass().getResource("/lib/load/v_loadTaux.fxml")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
