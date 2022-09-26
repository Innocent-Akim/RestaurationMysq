/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lib.app.Datasource;
import lib.app.DateOfday;


/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class DashboardController implements Initializable {

    @FXML
    private Label agents;
    @FXML
    private VBox bn;
    @FXML
    private Label clients;
    @FXML
    private Label dateDay;
    @FXML
    private Label mothOfyear;
    @FXML
    private Label jour;
    @FXML
    private AnchorPane sc;
    @FXML
    private Text bn1;
    @FXML
    private Label produit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        agents.setText(Datasource.getValue("SELECT COUNT(*) FROM personne WHERE type='AGENTS'"));
        clients.setText(Datasource.getValue("SELECT COUNT(*) FROM personne WHERE type='CLIENTS'"));
        produit.setText(Datasource.getValue("SELECT COUNT(*) FROM produits"));
        dateDay.setText(getDay(LocalDate.now().getDayOfMonth()));
        mothOfyear.setText(DateOfday.Pic(DateOfday.getMoth() + " " + LocalDate.now().getYear()));
        jour.setText(DateOfday.Pic(DateOfday.getWeekDay()));

    }

    String getDay(int jour) {
        return jour < 10 ? "0" + jour : jour + "";
    }

}
