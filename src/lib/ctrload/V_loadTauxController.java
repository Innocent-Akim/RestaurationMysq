/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lib.app.Datasource;
import static lib.ctrlgui.TauxController.public_datatype;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class V_loadTauxController implements Initializable {

    @FXML
    private JFXCheckBox index;
    @FXML
    private Label taux;
    @FXML
    private Label devise;
    public static String tauxString, deviseString, indexString;
    @FXML
    private Label status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 

        status.setText(indexString);
        taux.setText(tauxString);
        devise.setText(deviseString);
        if (status.getText().equals("0")) {
            index.setSelected(false);
        } else {
            index.setSelected(true);
        }
        index.setOnMouseClicked((event) -> {
            try {
                Datasource.execute("UPDATE taux SET status=?", "0");
                boolean et = Datasource.execute("UPDATE taux SET status=? WHERE taux=?", "1", taux.getText());
                if (et) {
                    init();
                }
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
