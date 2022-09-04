/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import lib.app.Datasource;
import lib.ctrlgui.V_FacturationController;

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
            ResultSet rs = Datasource.getrResultat("SELECT * FROM vs_facture WHERE refEntreprise='" + Datasource.refEntreprise + "' AND codeFacture='" + V_FacturationController.nameroLabel.getText() + "'");
            while (rs.next()) {
                compteur++;
                V_loadPrintController.codeString = rs.getString("id");
                V_loadPrintController.designationString = rs.getString("designation");
                V_loadPrintController.qtString = rs.getString("qte");
                V_loadPrintController.itemsString = compteur < 10 ? String.valueOf("0" + compteur) : String.valueOf(compteur);
                printFacture.getItems().add(FXMLLoader.load(getClass().getResource("/lib/uix/v_loadPrint.fxml")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
