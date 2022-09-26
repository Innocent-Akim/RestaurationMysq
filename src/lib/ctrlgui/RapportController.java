/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lib.app.Datasource;
import lib.app.manifest;
import lib.imprimer.Imprimer;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class RapportController implements Initializable {

    @FXML
    private AnchorPane btn_vente;
    @FXML
    private Label lbl_ventens;
    @FXML
    private Label lbl_ventes_date;
    @FXML
    private AnchorPane facture;
    @FXML
    private Label lbl_annuler;
    @FXML
    private Label lbl_annuler_date;
    @FXML
    private AnchorPane produit;
    @FXML
    private Label lbl_entree;
    @FXML
    private Label lbl_entree_date;
    @FXML
    private AnchorPane btn_menu;
    @FXML
    private Label lbl_sortie;
    @FXML
    private Label lbl_sortie_date;
    @FXML
    private GridPane cardeDate;
    @FXML
    private TextField date_debut;
    @FXML
    private JFXDatePicker calendarDebut;
    @FXML
    private TextField date_Fin;
    @FXML
    private JFXDatePicker calendarFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbl_ventens.setText("0.0");
        initEvent();
        calendarFin.setOnAction((action) -> {
            date_Fin.setText(calendarFin.getValue().toString().substring(0, 10));
            total();
        });
        calendarDebut.setOnAction((action) -> {
            date_debut.setText(calendarDebut.getValue().toString().substring(0, 10));
            total();
        });
    }

    void total() {
        try {
            lbl_ventens.setText(Datasource.getValue("SELECT sum(qte*pu) FROM vs_facture1 WHERE date between '" + date_debut.getText() + "' AND '" + date_Fin.getText() + "'"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void initEvent() {
        btn_menu.setOnMouseClicked((e) -> {
            Map<String, String> map = new HashMap();
            map.put("logo", manifest.RAPPORT_LOGO);
            new Imprimer().imprimer("SELECT * FROM `vs_produits` ORDER BY categorie", map, "menu_rea");
        });
        btn_vente.setOnMouseClicked((action) -> {
            Map<String, String> map = new HashMap();
            map.put("logo", manifest.RAPPORT_LOGO);
            new Imprimer().imprimer("SELECT * FROM vs_facture1 WHERE date Between '" + date_debut.getText() + "' AND '" + date_Fin.getText() + "'", map, "rapportVenteParDate");
        });
    }

}
