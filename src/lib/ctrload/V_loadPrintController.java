/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lib.app.Datasource;
import lib.ctrlgui.V_FacturationController;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class V_loadPrintController implements Initializable {

    @FXML
    private JFXButton btn_annuler;
    @FXML
    private Label designation;
    @FXML
    private Label items;
    @FXML
    private Label qte;
    public static String designationString, itemsString, qtString, codeString;
    @FXML
    private Label code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        designation.setText(designationString);
        qte.setText(qtString);
        items.setText(itemsString);
        code.setText(codeString);
        btn_annuler.setOnAction((action) -> {
            boolean status = Datasource.execute("DELETE FROM detailFacture WHERE code=?", code.getText().trim());
            if (status) {
                init();
            }

        });

    }

    void init() {
        try {
            int compteur = 0;
            Datasource.cleanList(V_printFactureController.printFactureView);
            ResultSet rs = Datasource.getrResultat("SELECT * FROM vs_facture WHERE refEntreprise='" + Datasource.refEntreprise + "' AND codeFacture='" + V_FacturationController.nameroLabel.getText() + "'");
            while (rs.next()) {
                compteur++;
                V_loadPrintController.codeString = rs.getString("id");
                V_loadPrintController.designationString = rs.getString("designation");
                V_loadPrintController.qtString = rs.getString("qte");
                V_loadPrintController.itemsString = compteur < 10 ? String.valueOf("0" + compteur) : String.valueOf(compteur);
                V_printFactureController.printFactureView.getItems().add(FXMLLoader.load(getClass().getResource("/lib/uix/v_loadPrint.fxml")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
