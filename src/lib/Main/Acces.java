/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.Main;

import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import lib.app.Vars;
import lib.ctrload.LoadTacheController;
import static lib.dbconnect.Dbconnect.cnx;

/**
 *
 * @author ISDR
 */
public final class Acces {

    ResultSet resultSet;
    public static ArrayList<String> listeAcces = new ArrayList();
    public static ArrayList<String> datadisp = new ArrayList();
    public static String refEntreprise = "1";

    public static ArrayList<String> getListeAcces() {
        return listeAcces;
    }

    public static void setListeAcces(ArrayList<String> listeAcces) {
        Acces.listeAcces = listeAcces;
    }

    public Acces() {

    }

    public Acces(String codeUser) {
        listeAcces = initData("SELECT fonction FROM Ttaches WHERE  refEntreprise= '" + Vars.vars.getRefEntreprise() + "' AND codeUser='" + codeUser + "' AND etat=1");
    }

    public static void setAcces(Node element, String fonction) {

        if (!listeAcces.contains(fonction)) {
            element.setDisable(true);
        }
    }

    ArrayList<String> initData(String query) {
        ArrayList<String> list = new ArrayList();
        try {
            list.clear();
            resultSet = cnx().createStatement().executeQuery(query);
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
        }
        return list;
    }

    public static ArrayList<String> initTach() {
        datadisp.add("Dashboard");
        datadisp.add("Facturation");
        datadisp.add("Operation");
        datadisp.add("Rapport");
        datadisp.add("Parametre");
        datadisp.add("Personnel");
        datadisp.add("Utilisateur");
        datadisp.add("Ventes");
        datadisp.add("Nouveau");
        datadisp.add("Caisse");
        datadisp.add("Taux");
        datadisp.add("Tableau de bord");
        return datadisp;
    }

    public void dataTache(JFXListView list) {
        list.getItems().clear();
        initTach().forEach((data) -> {
            try {
                LoadTacheController.taskpublc = data;
                list.getItems().add(FXMLLoader.load(getClass().getResource("/lib/load/loadTache.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
