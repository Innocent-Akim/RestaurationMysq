/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.app;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.controlsfx.control.PopOver;

/**
 *
 * @author ISDR
 */
public class App {

    private static App instance;

    public static App getInstance() {
        return instance == null ? instance = new App() : instance;
    }
    public static PopOver over = new PopOver();

    public static void popOverMenu(Node node, URL url, PopOver.ArrowLocation arrowLocation) throws IOException {
        if (!over.isShowing()) {
            AnchorPane box = FXMLLoader.load(url);
            over.setArrowLocation(arrowLocation);
            over.setAutoHide(true);
            over.setContentNode(box);
//            over.getStyle().
            over.show(node, 0);
        } else {
            over.hide();
        }
    }

    public void IsSeleted(Label... lab) {
        try {
            for (Label tr : lab) {
                tr.setTextFill(Color.web("#8b8f98"));
                tr.setFont(Font.font("Century Gothic", FontPosture.REGULAR, 13));
            }
            lab[0].setTextFill(Color.web("#F6AD2A"));
            lab[0].setFont(Font.font("Century Gothic", FontWeight.BOLD, 13));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SelectDataFor(Button... bt) {
        try {
            for (Button tr : bt) {
                tr.setStyle("-fx-background-color: #fff;-fx-text-fill: #000000;");
                tr.setFont(Font.font("Century Gothic", FontPosture.REGULAR, 13));
            }
            bt[0].setStyle("-fx-background-color: #F6AD2A;-fx-text-fill: #fff;");
            bt[0].setFont(Font.font("Century Gothic", FontWeight.BOLD, 13));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
