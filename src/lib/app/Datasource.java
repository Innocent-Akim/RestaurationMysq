/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.app;

import java.sql.SQLException;
import javafx.scene.control.Alert;
import lib.dbconnect.Dbconnect;

/**
 *
 * @author ISDR
 */
public class Datasource extends Dbconnect {

    public static boolean execute(String query, String... param) {
        try {
            preparedStatement = cnx().prepareStatement(query);
            int i = 1;
            if (param != null) {
                for (String string : param) {
                    preparedStatement.setString(i, string);
                    i++;
                }
            }
            return !preparedStatement.execute();

        } catch (SQLException e) {
            Files.error(query + "==>Error " + e.getMessage());
            Neurohub.neurohub.setAlert("Une erreur s'est produite." + e.getMessage(), Alert.AlertType.WARNING);
            System.err.println(query + "-->>" + e.getMessage());
        }
        return false;
    }
}
