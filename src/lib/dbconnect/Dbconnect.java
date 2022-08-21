/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.dbconnect;

import CallWindow.Callwindow;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author ISDR
 */
public class Dbconnect {

    private static Connection connexion;
    public static ResultSet resultSet;
    public static PreparedStatement preparedStatement;
    public static CallableStatement call;

    public Dbconnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbRestaurant", "root", "Akim12345@@");
        } catch (ClassNotFoundException | SQLException ex) {
            Callwindow.DialogFx(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public static Connection cnx() {
        if (connexion == null) {
            new Dbconnect();
        }
        return connexion;
    }

    public static boolean test() {
        new Dbconnect();
        return connexion != null;
    }
}
