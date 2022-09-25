/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lib.app.App;
import lib.app.Datasource;
import lib.app.Vars;
import lib.ctrload.V_loadCategorieController;
import lib.ctrload.V_loadTableController;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class V_FacturationController implements Initializable {

    @FXML
    private JFXListView<?> ListClient;
    @FXML
    private JFXListView<?> ListCagorie;
    @FXML
    private JFXListView<?> ListProduit;
    @FXML
    private JFXListView<?> ListFacture;
    public static JFXListView<?> ListProduitView;
    public static JFXListView<?> ListFactureView;
    @FXML
    private Label namero;
    public static Label nameroLabel;
    @FXML
    private Label tauxjour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nameroLabel = namero;
        ListProduitView = ListProduit;
        ListFactureView = ListFacture;
        tauxjour.setText(Datasource.getValue("SELECT taux FROM taux WHERE status=1") + " CDF");
        initLoad();

    }

    void initLoad() {
        try {
            int index = 0;
            Datasource.cleanList(ListClient, ListCagorie, ListProduit, ListFacture);
            ResultSet rs = Datasource.getrResultat("SELECT * FROM personne WHERE type='CLIENTS' AND refEntreprise='" + Vars.vars.getRefEntreprise() + "'");
            while (rs.next()) {
                V_loadTableController.idString = Integer.valueOf(rs.getString("code")) < 10 ? "0" + rs.getString("code") : rs.getString("code");
                V_loadTableController.nameClientString = rs.getString("nom").trim().toUpperCase();
                ListClient.getItems().add(FXMLLoader.load(getClass().getResource("/lib/load/v_loadTable.fxml")));
            }
            ResultSet rst = Datasource.getrResultat("SELECT * FROM categorie WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "'");
            while (rst.next()) {
                V_loadCategorieController.nameCategorieString = rst.getString("designation").trim().toUpperCase();
                V_loadCategorieController.idCodString = rst.getString("code").trim().toUpperCase();
                ListCagorie.getItems().add(FXMLLoader.load(getClass().getResource("/lib/load/v_loadCategorie.fxml")));
            }
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}
