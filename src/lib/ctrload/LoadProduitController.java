/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import static champs.champs_vide.isFieldsempty;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lib.app.Alerte;
import lib.app.Datasource;
import lib.app.Msg;
import lib.app.Neurohub;
import lib.ctrlgui.ProduitsController;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class LoadProduitController implements Initializable {

    @FXML
    private TextField designationFld;
    @FXML
    private TextField categorieFld;
    @FXML
    private Label codeBarLbl;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXButton btn_modifier;

    public static String designationString, punitaireString, categorieString, codeBaString;
    @FXML
    private TextField punitaire;
    @FXML
    private Label index;
    @FXML
    private ComboBox<String> categoriecbx;
    boolean isUpdate = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        designationFld.setText(designationString.trim().toUpperCase());
        punitaire.setText(punitaireString.trim());
        categorieFld.setText(categorieString.trim().toUpperCase());
        codeBarLbl.setText(codeBaString.trim());
        index.setText(designationString.substring(0, 2));
        categoriecbx.setVisible(false);
        Neurohub.neurohub.initFieldClean(designationFld, punitaire, categorieFld);
        Datasource.loadCombo(categoriecbx, "SELECT designation FROM `categorie` WHERE refEntreprise='" + Datasource.refEntreprise + "'");
        btn_modifier.setOnAction((e) -> {
            if (isUpdate == false) {
                categoriecbx.setValue(categorieFld.getText());
            }
            categorieFld.setVisible(false);
            categoriecbx.setVisible(true);
            Neurohub.neurohub.initFieldActive(designationFld, punitaire);
            if (isUpdate == true) {

                categorieFld.setText(categoriecbx.getValue().trim());
                String refCategorie = Datasource.getValue("SELECT code FROM categorie WHERE refEntreprise='" + Datasource.refEntreprise + "' AND designation='" + categorieFld.getText() + "'");
                boolean status = Datasource.execute("UPDATE `produits` SET `designation`=?,`pu`=?,`codeCategorie`=? WHERE code=?", designationFld.getText(), punitaire.getText(), refCategorie, codeBarLbl.getText().trim());
                if (status) {
                    isUpdate = false;
                    getLoaded();
                    categorieFld.setVisible(true);
                    categoriecbx.setVisible(false);
                    Neurohub.neurohub.initFieldClean(designationFld, punitaire, categorieFld);
                }
            }
            isUpdate = true;
        });
        btn_delete.setOnAction((action) -> {
            boolean status = Datasource.execute("DELETE FROM `produits` WHERE code=? ", codeBarLbl.getText());
            if (status) {
                getLoaded();
            }

        });
    }

    void getLoaded() {
        try {
            Datasource.cleanList(ProduitsController.itemProduitView);
            ResultSet rs = Datasource.getrResultat("SELECT * FROM vs_produits WHERE refEntreprise='" + Datasource.refEntreprise + "'");
            while (rs.next()) {
                LoadProduitController.designationString = rs.getString("designation");
                LoadProduitController.punitaireString = rs.getString("pu");
                LoadProduitController.categorieString = rs.getString("categorie");
                LoadProduitController.codeBaString = rs.getString("code");
                ProduitsController.itemProduitView.getItems().add(FXMLLoader.load(getClass().getResource("/lib/load/loadProduit.fxml")));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
