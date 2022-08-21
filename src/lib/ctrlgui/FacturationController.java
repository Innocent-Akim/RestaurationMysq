/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    private TextField code_client;
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
    private ListView<?> listClient;
    @FXML
    private ListView<?> listproduit_search;
    @FXML
    private Text taux_;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
