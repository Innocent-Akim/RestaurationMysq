/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import static champs.champs_vide.isFieldsempty;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lib.app.Alerte;
import lib.app.Datasource;
import lib.app.Msg;
import lib.app.Neurohub;
import lib.ctrload.LoadProduitController;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class AddProduitController implements Initializable {

    @FXML
    private ComboBox<String> categorieCbx;
    @FXML
    private JFXButton categorieBtn;
    @FXML
    private JFXButton enregistreBtn;
    @FXML
    private TextField designation;
    @FXML
    private TextField punitaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Datasource.loadCombo(categorieCbx, "SELECT designation FROM `categorie` WHERE refEntreprise='" + Datasource.refEntreprise + "'");
        categorieBtn.setOnAction((action) -> {
            showDialog();
        });
        enregistreBtn.setOnAction((action) -> {
            try {
                if (isFieldsempty(designation, punitaire, categorieCbx)) {
                    Alerte.alerteAvertissement("Attention", Msg.MESSAGE_ISMPTY);
                } else {
                    String refCategorie = Datasource.getValue("SELECT code FROM Categorie WHERE refEntreprise='" + Datasource.refEntreprise + "' AND designation='" + categorieCbx.getValue() + "'");
                    boolean status = Datasource.execute("INSERT INTO `produits` SET `designation`=?,`pu`=?,`codeCategorie`=?,`refEntreprise`=? ", designation.getText(), punitaire.getText(), refCategorie, Datasource.refEntreprise);
                    if (status) {
                        getLoaded();
                        Alerte.alerteInformation("Information", Msg.MESSAGE_SAVE);
                        Neurohub.initFields(designation, punitaire, categorieCbx);
                    } else {
                        Alerte.alerteErreur("ERROR", Msg.MESSAGE_NO_SAVE);
                    }
                }

            } catch (Exception e) {
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

    private void showDialog() {
        JFXDialogLayout layout = new JFXDialogLayout();
        JFXTextField txt_categorie = new JFXTextField();
        txt_categorie.setLabelFloat(true);
        txt_categorie.setPromptText("Entrez la catégorie");
        VBox box = new VBox();
        box.getChildren().add(txt_categorie);
        layout.setBody(box);
        JFXDialog dlg = new JFXDialog(PrincipalController.screenPane, layout, JFXDialog.DialogTransition.CENTER);
        JFXButton btn_add = new JFXButton("Ajouter");
        btn_add.setOnAction((e) -> {
            if (isFieldsempty(txt_categorie)) {
            } else {
                String refCategorie = Datasource.getValue("SELECT code FROM Categorie WHERE refEntreprise='" + Datasource.refEntreprise + "' AND designation='" + txt_categorie.getText() + "'");
                if (refCategorie != null) {
                    Neurohub.neurohub.setAlert("Nous ne pouvons pas enregistrer la catégorie qui existait déjà dans la base de données", Alert.AlertType.WARNING);
                } else {
                    boolean status = Datasource.execute("INSERT INTO `Categorie` SET `designation`=?,`refEntreprise`=? ", txt_categorie.getText(), Datasource.refEntreprise);
                    if (status) {
                        Alerte.alerteInformation("Information", Msg.MESSAGE_SAVE);
                        Neurohub.initFields(txt_categorie);
                        Datasource.loadCombo(categorieCbx, "SELECT designation FROM `categorie` WHERE refEntreprise='" + Datasource.refEntreprise + "'");
                    }
                }
            }
        });
        JFXButton btn_close = new JFXButton("Fermer");
        btn_close.setOnAction((e) -> {
            Datasource.loadCombo(categorieCbx, "SELECT designation FROM `categorie` WHERE refEntreprise='" + Datasource.refEntreprise + "'");
            dlg.close();
        });
        layout.setActions(btn_add, btn_close);
        dlg.show();
    }

}
