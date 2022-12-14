/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

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
public class V_loadProduitController implements Initializable {

    @FXML
    private Label designation;
    @FXML
    private Label pu;
    @FXML
    private Label categorie;
    @FXML
    private Label etat;
    @FXML
    private Label id;
    public static String designationString, puString, categorieString, etatString, idString, deviseString;
    @FXML
    private AnchorPane cardProduit;
    V_loadTableController loadTable;
    @FXML
    private Label devise;
    public static Label puLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        puLabel = pu;
        loadTable = new V_loadTableController();
        designation.setText(designationString);
        pu.setText(puString);
        categorie.setText(categorieString);
        etat.setText(etatString);
        id.setText(idString);
        devise.setText(deviseString);
        cardProduit.setOnMouseClicked((action) -> {
            int qte = 1;
            System.out.println(Vars.vars.getRefEntreprise());
            String codeProduit = Datasource.getValue("SELECT code FROM produits WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND Designation='" + designation.getText().trim() + "'");
            String qteD = Datasource.getValue("SELECT qte FROM vs_facture WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND codeProduit='" + codeProduit.trim() + "' AND  codeFacture='" + V_FacturationController.nameroLabel.getText().trim() + "'");
            String exist = Datasource.getValue("SELECT id FROM vs_facture WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND codeProduit='" + codeProduit.trim() + "' AND codeFacture='" + V_FacturationController.nameroLabel.getText().trim() + "'"
            );
            if (V_FacturationController.nameroLabel.getText().equals("00")) {
                V_FacturationController.nameroLabel.setText(Neurohub.neurohub.createFacture("ORDINAIRE"));
                Datasource.execute("INSERT INTO detailfacture SET qte=?, pu=?, codeProduit=?, codeFacture=?", String.valueOf(qte), pu.getText(), codeProduit.trim(), V_FacturationController.nameroLabel.getText().trim());
            } else {
                if (exist == null) {
                    Datasource.execute("INSERT INTO detailfacture SET qte=?, pu=?, codeProduit=?, codeFacture=?", String.valueOf(qte), pu.getText(), codeProduit.trim(), V_FacturationController.nameroLabel.getText().trim());
                } else {
                    Datasource.execute("UPDATE  detailfacture SET qte=?  WHERE code=?", String.valueOf((Integer.valueOf(qteD) + qte)), exist);
                }
            }
            iniFacture();
            initAmount(V_FacturationController.nameroLabel.getText().trim(), Float.valueOf(pu.getText()));

        });

//        btn_close.setOnMouseClicked((action) -> {
//            int qte = 1;
//            String codeProduit = Datasource.getValue("SELECT code FROM produits WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND Designation='" + designation.getText().trim() + "'");
//            String qteD = Datasource.getValue("SELECT qte FROM vs_facture WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "' AND codeProduit='" + codeProduit.trim() + "' AND  codeFacture='" + V_FacturationController.nameroLabel.getText().trim() + "'");
//            String exist = Datasource.getValue("SELECT id FROM vs_facture WHERE refEntreprise='" +Vars.vars.getRefEntreprise()  + "' AND codeProduit='" + codeProduit.trim() + "' AND codeFacture='" + V_FacturationController.nameroLabel.getText() + "'");
//            if (exist != null && Integer.valueOf(qteD) > 1) {
//                Datasource.execute("UPDATE  detailfacture SET qte=? WHERE code=?", String.valueOf((Integer.valueOf(qteD) - qte)), exist);
//            } else {
//                Datasource.execute("DELETE FROM  detailfacture WHERE code=?", exist);
//            }
//            iniFacture();
//        });
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

    boolean initAmount(String idFacture, float montant) {
        try {
            return Datasource.execute("UPDATE entetefacture SET montant=montant+? WHERE code=?", String.valueOf(montant), idFacture);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
