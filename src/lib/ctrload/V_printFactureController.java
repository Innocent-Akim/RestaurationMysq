/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lib.app.Datasource;
import lib.app.Vars;
import lib.app.manifest;
import lib.ctrlgui.V_FacturationController;
import lib.imprimer.Imprimer;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class V_printFactureController implements Initializable {

    @FXML
    private JFXListView<?> printFacture;
    @FXML
    private JFXButton btn_print;
    @FXML
    private JFXButton btn_delete;
    public static JFXListView<?> printFactureView;
    @FXML
    private Label codeFacture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        printFactureView = printFacture;
        init();
    }

    void init() {
        try {
            int compteur = 0;
            Datasource.cleanList(printFacture);
            ResultSet rs = Datasource.getrResultat("SELECT * FROM vs_facture WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND codeFacture='" + V_FacturationController.nameroLabel.getText() + "'");
            while (rs.next()) {
                compteur++;
                V_loadPrintController.codeString = rs.getString("id");
                V_loadPrintController.designationString = rs.getString("designation");
                V_loadPrintController.qtString = rs.getString("qte");
                V_loadPrintController.itemsString = compteur < 10 ? String.valueOf("0" + compteur) : String.valueOf(compteur);
                printFacture.getItems().add(FXMLLoader.load(getClass().getResource("/lib/uix/v_loadPrint.fxml")));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        btn_print.setOnAction((action) -> {
            try {
                Map<String, String> map = new HashMap();
                map.put("img", manifest.RAPPORT_LOGO);
                for (int x = 0; x < 2; x++) {
                    new Imprimer().isPrint("SELECT * FROM `vs_facture` where codeFacture='" + V_FacturationController.nameroLabel.getText() + "'", "facture", map, Boolean.TRUE);
                }
            } catch (Exception e) {
            }
        });

        btn_delete.setOnAction((action) -> {
            try {
                boolean status = false;
                ResultSet rs = Datasource.getrResultat("SELECT * FROM vs_facture1 WHERE codeFacture='" + V_FacturationController.nameroLabel.getText() + "'");
                while (rs.next()) {
                    status = Datasource.execute("INSERT INTO historique SET  `qte`=?, `pu`=?, `date`=?, `codeFacture`=?, `codeProduit`=?, `codeAgent`=?, `codeClient`=?",
                            rs.getString("qte"),
                            rs.getString("pu"),
                            rs.getString("date"),
                            rs.getString("codeFacture"),
                            rs.getString("codeProduit"),
                            rs.getString("agent"),
                            rs.getString("codeClient")
                    );
                }
                if (status) {
                    if (Datasource.execute("DELETE FROM enteteFacture WHERE code=?", V_FacturationController.nameroLabel.getText())) {
                        Datasource.execute("DELETE FROM detailfacture WHERE codeFacture=?", V_FacturationController.nameroLabel.getText());
                        init();
                        V_FacturationController.nameroLabel.setText("00");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
