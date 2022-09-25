/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lib.app.Datasource;
import lib.app.Neurohub;
import lib.app.Vars;
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
    @FXML
    private AnchorPane card;
    @FXML
    private JFXButton plus;
    @FXML
    private JFXButton moins;

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

        plus.setOnAction((event) -> {
            int qte = 1;
            System.out.println(Vars.vars.getRefEntreprise());
            String codeProduit = Datasource.getValue("SELECT code FROM produits WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND Designation='" + designation.getText().trim() + "'");
            String qteD = Datasource.getValue("SELECT qte FROM vs_facture WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND codeProduit='" + codeProduit.trim() + "' AND  codeFacture='" + V_FacturationController.nameroLabel.getText().trim() + "'");
            String exist = Datasource.getValue("SELECT id FROM vs_facture WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND codeProduit='" + codeProduit.trim() + "' AND codeFacture='" + V_FacturationController.nameroLabel.getText().trim() + "'"
            );
            if (V_FacturationController.nameroLabel.getText().equals("00")) {
                V_FacturationController.nameroLabel.setText(Neurohub.neurohub.createFacture("ORDINAIRE"));
                Datasource.execute("INSERT INTO detailfacture SET qte=?, pu=?, codeProduit=?, codeFacture=?", String.valueOf(qte), V_loadProduitController.puLabel.getText(), codeProduit.trim(), V_FacturationController.nameroLabel.getText().trim());
            } else {
                if (exist == null) {
                    Datasource.execute("INSERT INTO detailfacture SET qte=?, pu=?, codeProduit=?, codeFacture=?", String.valueOf(qte), V_loadProduitController.puLabel.getText(), codeProduit.trim(), V_FacturationController.nameroLabel.getText().trim());
                } else {
                    Datasource.execute("UPDATE  detailfacture SET qte=?  WHERE code=?", String.valueOf((Integer.valueOf(qteD) + qte)), exist);
                }
            }
            init();
            initAmount(V_FacturationController.nameroLabel.getText().trim(), Float.valueOf(V_loadProduitController.puLabel.getText()));
        });
        moins.setOnAction((event) -> {
            int qte = 1;
            String codeProduit = Datasource.getValue("SELECT code FROM produits WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND Designation='" + designation.getText().trim() + "'");
            String qteD = Datasource.getValue("SELECT qte FROM vs_facture WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND codeProduit='" + codeProduit.trim() + "' AND  codeFacture='" + V_FacturationController.nameroLabel.getText().trim() + "'");
            String exist = Datasource.getValue("SELECT id FROM vs_facture WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND codeProduit='" + codeProduit.trim() + "' AND codeFacture='" + V_FacturationController.nameroLabel.getText() + "'");
            if (exist != null && Integer.valueOf(qteD) > 1) {
                Datasource.execute("UPDATE  detailfacture SET qte=? WHERE code=?", String.valueOf((Integer.valueOf(qteD) - qte)), exist);
            } else {
                Datasource.execute("DELETE FROM  detailfacture WHERE code=?", exist);
            }
            init();
            initAmoun(V_FacturationController.nameroLabel.getText().trim(), Float.valueOf(V_loadProduitController.puLabel.getText()));

        });

    }

    boolean initAmoun(String idFacture, float montant) {
        try {
            return Datasource.execute("UPDATE entetefacture SET montant=montant-? WHERE code=?", String.valueOf(montant), idFacture);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    boolean initAmount(String idFacture, float montant) {
        try {
            return Datasource.execute("UPDATE entetefacture SET montant=montant+? WHERE code=?", String.valueOf(montant), idFacture);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    void iniFacture() {
        try {
            String montantCDF = Datasource.getValue("SELECT taux FROM taux WHERE status=1");
            Datasource.cleanList(V_FacturationController.ListFactureView);
            System.out.println(V_FacturationController.nameroLabel.getText());
            ResultSet rs = Datasource.getrResultat("SELECT sum(qte*pu) montant,codeFacture,codeClient,client,devise FROM vs_facture WHERE codeFacture='" + V_FacturationController.nameroLabel.getText() + "' AND refEntreprise='" + Vars.vars.getRefEntreprise() + "'"
                    + "  GROUP BY codeFacture,codeClient,client,devise");
            while (rs.next()) {
                V_loadFactureController.montantString = rs.getString("montant") + " " + rs.getString("devise");
                V_loadFactureController.idString = Integer.valueOf(rs.getString("codeClient")) < 10 ? "0" + rs.getString("codeClient") : rs.getString("codeClient");
                V_loadFactureController.numString = rs.getString("codeFacture");
                V_loadFactureController.clientString = rs.getString("client");
                V_loadFactureController.montanDCFString = String.valueOf(Float.valueOf(montantCDF) * rs.getFloat("montant")) + " CDF";
                V_FacturationController.ListFactureView.getItems().add(FXMLLoader.load(getClass().getResource("/lib/load/v_loadFacture.fxml")));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    void init() {
        try {
            int compteur = 0;
            Datasource.cleanList(V_printFactureController.printFactureView);
            ResultSet rs = Datasource.getrResultat("SELECT * FROM vs_facture WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND codeFacture='" + V_FacturationController.nameroLabel.getText() + "'");
            while (rs.next()) {
                compteur++;
                V_loadPrintController.codeString = rs.getString("id");
                V_loadPrintController.designationString = rs.getString("designation");
                V_loadPrintController.qtString = rs.getString("qte");
                V_loadPrintController.itemsString = compteur < 10 ? String.valueOf("0" + compteur) : String.valueOf(compteur);
                V_printFactureController.printFactureView.getItems().add(FXMLLoader.load(getClass().getResource("/lib/uix/v_loadPrint.fxml")));
            }
            iniFacture();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
