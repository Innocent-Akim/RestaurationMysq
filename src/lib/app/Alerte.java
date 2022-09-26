/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.app;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author ISDR
 */
public class Alerte {

    public static Alerte alerte = new Alerte();

    public static boolean alertQuestion(String header, String txte) {
        boolean val;
        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
        alerte.setTitle("Fenetre");
        alerte.setHeaderText(header);
        alerte.setContentText(txte);
        Optional<ButtonType> resul = alerte.showAndWait();
        val = resul.get() == ButtonType.OK;
        return val;
    }

    public static void alerteInformation(String titre, String message) {
        Notifications notificationBuilder = Notifications.create()
                .title(titre)
                .text("\n                               " + message)
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_RIGHT)
                .onAction((ActionEvent event) -> {
                });

        notificationBuilder.showInformation();
    }

    public static void alerteAvertissement(String titre, String message) {
        Notifications notificationAvertissement;
        notificationAvertissement = Notifications.create()
                .title(titre)
                .text("\n                                 " + message)
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_RIGHT)
                .onAction((ActionEvent event) -> {
                });
        notificationAvertissement.showWarning();
    }

    public static void alerteErreur(String titre, String message) {

        Notifications notificationsErreur = Notifications.create()
                .title(titre)
                .text("\n                                 " + message)
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_RIGHT)
                .onAction((ActionEvent event) -> {
                });
        notificationsErreur.showError();

    }

    public static boolean question(StackPane a, String b) {

        JFXButton b1 = new JFXButton("OK");
        JFXButton b2 = new JFXButton("Annuler");
        JFXDialogLayout Layout = new JFXDialogLayout();
        Layout.setHeading(new Label(b));
        JFXDialog dialog = new JFXDialog(a, Layout, JFXDialog.DialogTransition.CENTER);
        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            System.exit(0);
        });
        b2.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            dialog.close();
        });
        Layout.setActions(b1, b2);
        dialog.show();
        return true;
    }

    public static void showFormDialog(String title, StackPane rootPane, URL location, JFXDialog.DialogTransition transition) throws IOException {
        JFXDialog dialog;
        Node node = FXMLLoader.load(location);
        JFXDialogLayout dl = new JFXDialogLayout();
        dl.setPadding(new Insets(20, 10, 20, 10));
        dl.setHeading(new Label(title));
        dl.setBody(node);
        dialog = new JFXDialog(rootPane, dl, transition, false);
        JFXButton ok = new JFXButton("Fermer");
        ok.setOnAction(e -> {
            dialog.close();
        });

        dl.setActions(ok);
        dialog.show(rootPane);

    }

    public boolean setAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Alert");
        alert.initStyle(StageStyle.DECORATED);
        alert.setHeaderText(message);
        Stage stages = (Stage) alert.getDialogPane().getScene().getWindow();
        stages.getIcons().add(new Image(this.getClass().getResource(manifest.APP_ICON).toString()));
        Optional<ButtonType> result = alert.showAndWait();
        return (result.get() == ButtonType.OK);
    }
}
