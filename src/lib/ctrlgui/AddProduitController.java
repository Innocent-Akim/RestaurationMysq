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
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class AddProduitController implements Initializable {

    @FXML
    private ComboBox<?> categorieCbx;
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
        categorieBtn.setOnAction((action) -> {
            showDialog();
        });
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

            }

        });
        JFXButton btn_close = new JFXButton("Fermer");
        btn_close.setOnAction((e) -> {
            dlg.close();

        });
        layout.setActions(btn_add, btn_close);
        dlg.show();
    }
}
