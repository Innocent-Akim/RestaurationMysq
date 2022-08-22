/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lib.app.Datasource;
import lib.app.Files;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class FacturationController implements Initializable {

    @FXML
    private AnchorPane interfaceVente;
    @FXML
    private Button btn_calculatrice;
    @FXML
    private TextField designation;
    @FXML
    private TextField qte;
    @FXML
    private TextField punitaire;
    @FXML
    private TextField pT;
    @FXML
    private JFXButton enregitrebtn;
    @FXML
    private Label numerF;
    @FXML
    private JFXButton print1;
    @FXML
    private JFXButton annuler_facture;
    @FXML
    private Label client_nom;
    @FXML
    private Label code_produit_;
    @FXML
    private Button btn_addCLient;
    @FXML
    private JFXListView<?> vb_vente;
    @FXML
    private JFXButton ch_left;
    @FXML
    private JFXButton ch_right;
    @FXML
    private Label pagetext;
    @FXML
    private TextField recher_facture;
    @FXML
    private Label montant_payer;
    @FXML
    private Label taux_1;
    @FXML
    private Label quantite_;
    @FXML
    private ListView<String> listClient;
    @FXML
    private ListView<String> listproduit_search;
    @FXML
    private Text taux_;
    @FXML
    private TextField nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listClient.setVisible(false);
        listproduit_search.setVisible(false);
        nom.requestFocus();
        nom.setOnKeyReleased((KeyEvent action) -> {
            try {
                listClient.getItems().clear();
                listClient.setVisible(true);
                ResultSet rs = Datasource.getrResultat("SELECT * FROM personne WHERE nom LIKE '%" + nom.getText() + "%' AND refEntreprise='" + Datasource.refEntreprise + "' AND type='CLIENTS'");
                while (rs.next()) {
                    listClient.getItems().addAll(rs.getString("nom"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        });
        listClient.setOnMouseClicked((action) -> {
            listClient.setVisible(false);
            nom.setText(listClient.getSelectionModel().getSelectedItem());
            designation.requestFocus();
        });
        listClient.setOnMouseExited((action) -> {
            listClient.setVisible(false);
        });
        designation.setOnKeyReleased((KeyEvent action) -> {
            try {
                listproduit_search.getItems().clear();
                listproduit_search.setVisible(true);
                ResultSet rs = Datasource.getrResultat("SELECT * FROM produits WHERE designation LIKE '%" + designation.getText() + "%' AND refEntreprise='" + Datasource.refEntreprise + "'");
                while (rs.next()) {
                    listproduit_search.getItems().addAll(rs.getString("designation"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        });
        listproduit_search.setOnMouseClicked((action) -> {
            listproduit_search.setVisible(false);
            designation.setText(listproduit_search.getSelectionModel().getSelectedItem());
            try {
                ResultSet rs = Datasource.getrResultat("SELECT * FROM produits WHERE designation='" + designation.getText().trim() + "' AND refEntreprise='" + Datasource.refEntreprise + "'");
                if (rs.next()) {
                    punitaire.setText(rs.getString("pu"));
                    qte.setText("1");
                    pT.setText(String.valueOf(Float.valueOf(qte.getText()) * Float.valueOf(punitaire.getText())));
                    qte.requestFocus();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        });
        qte.setOnKeyReleased((action) -> {
            try {
                if (qte.getText().trim().equals("")) {
                    pT.setText("0.0");
                }
                pT.setText(String.valueOf(Float.valueOf(qte.getText()) * Float.valueOf(punitaire.getText())));
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        });
        listproduit_search.setOnMouseExited((action) -> {
            listproduit_search.setVisible(false);
        });
        listClient.setOnMouseClicked((action) -> {
            listClient.setVisible(false);
            nom.setText(listClient.getSelectionModel().getSelectedItem());
        });
        listClient.setOnMouseExited((action) -> {
            listClient.setVisible(false);
        });
    }

    @FXML
    private void ch_soust(MouseEvent event) {
    }

    @FXML
    private void ch_add(MouseEvent event) {
    }

    @FXML
    private void mouse_exit_false(MouseEvent event) {
    }

    String exec() {
        try {
//           
            String fact = Datasource.getValue("SELECT COALESCE(MAX(code),0)+1 FROM entetefacture WHERE refEntreprise='" + Datasource.refEntreprise + "'");
            boolean status = Datasource.execute(" INSERT INTO `entetefacture` SET `code`=?,`montant`=?,`codeClient`=?,`codeAgent`=?,`refEntreprise`=?' ", fact,"0",client_nom.getText());
            if (status) {

            }
        } catch (Exception e) {
        }
        return null;
    }
}
