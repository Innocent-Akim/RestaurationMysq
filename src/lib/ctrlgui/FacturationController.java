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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lib.app.Datasource;
import lib.app.Files;
import lib.app.Vars;
import lib.ctrload.LoadVenteController;

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
    public String codeProduit = "00";

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
        qte.setOnKeyReleased((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                exec();
                designation.requestFocus();
                System.out.println("OK OK");
            }
        });
        listproduit_search.setOnMouseClicked((action) -> {
            listproduit_search.setVisible(false);
            designation.setText(listproduit_search.getSelectionModel().getSelectedItem());
            try {
                ResultSet rs = Datasource.getrResultat("SELECT * FROM produits WHERE designation='" + designation.getText().trim() + "' AND refEntreprise='" + Datasource.refEntreprise + "'");
                if (rs.next()) {
                    punitaire.setText(rs.getString("pu"));
                    codeProduit = rs.getString("code");
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
            client_nom.setText(Datasource.getValue("SELECT code FROM personne WHERE nom='" + nom.getText() + "' AND refEntreprise='" + Datasource.refEntreprise + "' AND type='CLIENTS'"));
            if (numerF.getText().equals("00")) {
                numerF.setText(exec());
            }
            designation.requestFocus();

        });
        listClient.setOnMouseExited((action) -> {
            listClient.setVisible(false);
        });

        enregitrebtn.setOnAction((actin) -> {
            detail();
            designation.requestFocus();
            System.out.println("OK OK");
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
            String fact = Datasource.getValue("SELECT COALESCE(MAX(code),0)+1 FROM entetefacture WHERE refEntreprise='" + Datasource.refEntreprise + "'");
            boolean status = Datasource.execute(" INSERT INTO `entetefacture` SET `code`=?,`montant`=?,`codeClient`=?,`codeAgent`=?,`refEntreprise`=?", fact, "0", client_nom.getText(), Vars.vars.getCode(), Datasource.refEntreprise);
            if (status) {
                return Datasource.getValue("SELECT MAX(code) FROM `entetefacture` WHERE refEntreprise='" + Datasource.refEntreprise + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    void detail() {
        if (!codeProduit.equals("00")) {
            String fact = Datasource.getValue("SELECT detai.code FROM detailfacture detai,entetefacture entete  WHERE (detai.codeProduit=entete.code) AND codeProduit='" + codeProduit + "' AND codeFacture='" + numerF.getText() + "'");
            String quantite = Datasource.getValue("SELECT detai.qte FROM detailfacture detai,entetefacture entete  WHERE (detai.codeProduit=entete.code) AND codeProduit='" + codeProduit + "' AND codeFacture='" + numerF.getText() + "'");

            if (fact == null) {
                boolean status = Datasource.execute("INSERT INTO  `detailfacture` SET `pu`=?,`qte`=?,`codeFacture`=?,`codeProduit`=?", punitaire.getText(), qte.getText(), numerF.getText(), codeProduit);
                if (status) {
                    initData();
                }
            } else {
                String qt = String.valueOf(Float.valueOf(quantite) + Float.valueOf(this.qte.getText()));
                boolean status = Datasource.execute("UPDATE `detailfacture` SET `qte`=? WHERE `code`=?", qt.trim(), fact.trim());
                if (status) {
                    initData();
                }
            }
            boolean status = Datasource.execute("UPDATE entetefacture SET montant=? WHERE code=?", montant_payer.getText(), numerF.getText());
                if(status){
                
                }
        }
    }

    void initData() {
        try {
            Datasource.cleanList(vb_vente);
            ResultSet rs = Datasource.getrResultat("SELECT * FROM detailFacture detail, entetefacture entete, produits p WHERE (p.code=detail.codeProduit AND entete.code=detail.codeFacture) AND detail.codeFacture='" + numerF.getText() + "' AND entete.refEntreprise='" + Datasource.refEntreprise + "'");
            while (rs.next()) {
                LoadVenteController.designationString = rs.getString("designation");
                LoadVenteController.puString = rs.getString("pu");
                LoadVenteController.ptString = String.valueOf(rs.getFloat("qte") * rs.getFloat("pu"));
                LoadVenteController.qteString = rs.getString("qte");
                LoadVenteController.idFactureString = rs.getString("codeFacture");
                LoadVenteController.idString = rs.getString("detail.code");
                vb_vente.getItems().add(FXMLLoader.load(getClass().getResource("/lib/load/loadVente.fxml")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
