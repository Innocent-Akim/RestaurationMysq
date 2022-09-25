/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.app;

import com.jfoenix.controls.JFXListView;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import lib.dbconnect.Dbconnect;

/**
 *
 * @author ISDR
 */
public class Datasource extends Dbconnect {

    public static String refEntreprise;

    public Datasource() {
        refEntreprise = Vars.vars.getRefEntreprise();
    }

    public static int getCountBy(String rqt) {
        int x = 0;
        try {
            preparedStatement = cnx().prepareStatement(rqt);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                x++;
            }
        } catch (SQLException e) {
            Files.error(rqt + "==>Error " + e.getMessage());
            System.err.println(e);
        }
        return x;
    }

    public static boolean execute(String query, String... param) {
        try {
            preparedStatement = cnx().prepareStatement(query);
            int i = 1;
            if (param != null) {
                for (String string : param) {
                    preparedStatement.setString(i, string.trim().toUpperCase());
                    i++;
                }
            }
            return !preparedStatement.execute();

        } catch (SQLException e) {
            Files.error(query + "==>Error " + e.getMessage());
            Neurohub.neurohub.setAlert("Une erreur s'est produite." + e.getMessage(), Alert.AlertType.WARNING);
        }
        return false;
    }

    public static String getValue(String query) {
        try {
            preparedStatement = cnx().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException ex) {
            Files.error(query + "==>Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public static ResultSet getrResultat(String query) {
        try {
            preparedStatement = cnx().prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            Files.error(query + "==>Error " + e.getMessage());
        } finally {
            test();
        }
        return null;
    }

    public static void loadCombo(ComboBox<String> combo, String query) {
        try {
            combo.getItems().clear();
            preparedStatement = cnx().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!combo.getItems().contains(resultSet.getString(1))) {
                    combo.getItems().add(resultSet.getString(1));
                }
            }
        } catch (SQLException e) {
            Files.error(query + "==>Error " + e.getMessage());
        }
    }

    public static void cleanList(JFXListView... list) {
        for (JFXListView clean : list) {
            clean.getItems().clear();
        }
    }
}
