/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.Main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import lib.dbconnect.Dbconnect;
import vulembere.vulembereGUI;

/**
 *
 * @author ISDR
 */
public class Main extends Application {

    public static Acces acces = null;
    public static vulembereGUI stage = null;
    public static Stage primaryStage_;

//    public static MyLogin aut = new MyLogin();
//    public static Preferences ref = new Preferences();
//    public static Connexion connexion = new Connexion();
//    public static Consultations consult = new Consultations();
//    public static Menus menu = new Menus();
//    public static Ordonance ord = new Ordonance();
//    public static Stage primaryStage_;
//    @Override
//    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/lib/gui/Progressbar.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.getIcons().add(new javafx.scene.image.Image("/lib/image/LogoEMs.png"));
//        stage.show();
//    } 
    @Override
    public void start(Stage primaryStage) {
        stage = new vulembereGUI();
        connect();
        primaryStage_ = primaryStage;
        stage.getStage().getIcons().add(new javafx.scene.image.Image("/lib/img/logo.png"));
        stage.setTitle("SOLUTION DE GESTION ADAPTE A VOTRE METIER");
        stage.setResizable(true);        stage.setMaximized(true);
        stage.getScene().getStylesheets().add("/lib/css/material-color.css");
        stage.show();

    }

    void connect() {
        if (Dbconnect.cnx() != null) {
            stage.setContent(View.instance().get(View.LANDGINGPAGE));
        } else {
            stage.setContent(View.instance().get(View.ERROR));
        }

    }

    public static void getMax() {
        Main.stage.setMaximized(true);
        Main.stage.setResizable(true);
    }

    public static void getSize() {
        Main.stage.setMaximized(false);
        Main.stage.setResizable(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
