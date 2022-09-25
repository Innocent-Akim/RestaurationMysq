/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lib.app.App;
import lib.app.Datasource;
import lib.app.Vars;
import lib.ctrload.LoadProduitController;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class ProduitsController implements Initializable {

    @FXML
    private AnchorPane produit_interface;
    @FXML
    private AnchorPane itemList;
    @FXML
    private JFXListView<String> itemProduit;
    @FXML
    private TextField recher_facture;
    @FXML
    private JFXButton btn_add_produit;
    public static JFXListView<String> itemProduitView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemProduitView = itemProduit;
        getLoaded();
        btn_add_produit.setOnAction((action) -> {
            try {
                App.popOverMenu(btn_add_produit, getClass().getResource("/lib/gui/AddProduit.fxml"),
                        PopOver.ArrowLocation.TOP_CENTER);
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
            }
        });

    }

    void getLoaded() {
        try {
            Datasource.cleanList(itemProduit);
            ResultSet rs = Datasource.getrResultat("SELECT * FROM vs_produits WHERE refEntreprise='" + Vars.vars.getRefEntreprise() + "'");
            while (rs.next()) {
                LoadProduitController.designationString = rs.getString("designation");
                LoadProduitController.punitaireString = rs.getString("pu");
                LoadProduitController.categorieString = rs.getString("categorie");
                LoadProduitController.codeBaString = rs.getString("code");
                itemProduit.getItems().add(FXMLLoader.load(getClass().getResource("/lib/load/loadProduit.fxml")));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}
