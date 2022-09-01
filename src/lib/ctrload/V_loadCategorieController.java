/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lib.app.Datasource;
import lib.ctrlgui.V_FacturationController;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class V_loadCategorieController implements Initializable {

    @FXML
    private Label nameCategorie;
    public static String nameCategorieString, idCodString;
    @FXML
    private Label id;
    @FXML
    private AnchorPane cardCategorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCategorie.setText(nameCategorieString.trim());
        id.setText(idCodString);
        cardCategorie.setOnMouseClicked((action) -> {
            try {
                Datasource.cleanList(V_FacturationController.ListProduitView);
                ResultSet rs = Datasource.getrResultat("SELECT * FROM vs_produits WHERE categorie='" + nameCategorie.getText() + "' AND refEntreprise='" + Datasource.refEntreprise + "'");
                while (rs.next()) {
                    V_loadProduitController.designationString = rs.getString("designation");
                    V_loadProduitController.categorieString = rs.getString("categorie");
                    V_loadProduitController.puString = rs.getString("pu");
                    V_loadProduitController.idString = rs.getString("code");
                    V_loadProduitController.etatString = "Disponible";
                    V_FacturationController.ListProduitView.getItems().add(FXMLLoader.load(getClass().getResource("/lib/load/v_loadProduit.fxml")));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // TODO
    }

}
