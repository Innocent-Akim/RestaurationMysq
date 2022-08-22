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
import javafx.scene.control.ListView;
import lib.dbconnect.Dbconnect;

/**
 *
 * @author ISDR
 */
public class Datasource extends Dbconnect {

    public static String refEntreprise = "1";

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
        } catch (SQLException e) {
            Files.error(query + "==>Error " + e.getMessage());
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
