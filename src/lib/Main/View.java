/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.Main;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.controlsfx.control.PopOver;

/**
 *
 * @author ISDR
 */
public class View {

    public static Map<String, String> map = new HashMap();
    public static StackPane stackPane = Main.stage.getContent();
    public static final HashMap<String, Node> SCREENS = new HashMap<>();
    public static Node currentView = null;
    public static JFXDialog dialog;
    public static final PopOver over = new PopOver();
    public static String DASHBOARD = "dashboard";
    public static String HOME = "home";
    public static String VENTE = "ventes";
    public static String FOURNIR = "appro";
    public static String COMPTABILITE = "comptabilite";
    public static String SETTINGS = "parametre";
    public static String UTILISATEUR = "utilisateur";
    public static String PERSONNE = "loadPersonne";
    public static String FONCTION = "fonction";
    public static String LOGIN = "login";
    public static String CONFIGURATION = "configuration";
    public static String ERROR = "Error";
    public static String RAPPORT = "rapport";
    public static String STATISTIQUE = "statistique";
    public static String CAISSE = "caisse";
    public static String COMMANDE = "commande";
    public static String PRODUIT = "produits";
    public static String MARKETING = "marketing";
    public static String PROGRESS = "Progressbar";
    public static String AGENDA = "agenda";
    public static String LIST_COMPTE = "listCompte";
    public static String ECRITURE = "ecriture";
    public static String JOURNAL = "journal";
    public static String GRANDLIVRE = "grandLivre";
    public static String BALANCE = "balance";
    public static String STOCK = "stock";
    public static String DETTES = "dettes";
    private static View instance;

    private String getUri(String uri) {
        return "/lib/gui/" + uri + ".fxml";
    }

    public View() {
        map.put(HOME, getUri(HOME));
        map.put(DASHBOARD, getUri(DASHBOARD));
        map.put(LOGIN, getUri(LOGIN));
        map.put(CONFIGURATION, getUri(CONFIGURATION));
        map.put(SETTINGS, getUri(SETTINGS));
        map.put(FOURNIR, getUri(FOURNIR));
        map.put(ERROR, getUri(ERROR));
        map.put(COMPTABILITE, getUri(COMPTABILITE));
        map.put(RAPPORT, getUri(RAPPORT));
        map.put(STATISTIQUE, getUri(STATISTIQUE));
        map.put(CAISSE, getUri(CAISSE));
        map.put(PRODUIT, getUri(PRODUIT));
        map.put(COMMANDE, getUri(COMMANDE));
        map.put(VENTE, getUri(VENTE));
        map.put(PERSONNE, getUri(PERSONNE));
        map.put(FONCTION, getUri(FONCTION));
        map.put(UTILISATEUR, getUri(UTILISATEUR));
        map.put(MARKETING, getUri(MARKETING));
        map.put(PROGRESS, getUri(PROGRESS));
        map.put(AGENDA, getUri(AGENDA));
        map.put(LIST_COMPTE, getUri(LIST_COMPTE));
        map.put(ECRITURE, getUri(ECRITURE));
        map.put(JOURNAL, getUri(JOURNAL));
        map.put(GRANDLIVRE, getUri(GRANDLIVRE));
        map.put(BALANCE, getUri(BALANCE));
        map.put(STOCK, getUri(STOCK));
        map.put(DETTES, getUri(DETTES));

    }

    public static View instance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }
//

    public Node get(String view) {
        if (!SCREENS.containsKey(view)) {
            try {
                String url = map.get(view);
                if (url == null) {
                    SCREENS.put(view, FXMLLoader.load(getClass().getResource(ERROR)));
                } else {
                    currentView = FXMLLoader.load(getClass().getResource(url));
                    SCREENS.put(view, currentView);
                }

            } catch (IOException ex) {
                try {
                    SCREENS.put(view, FXMLLoader.load(getClass().getResource(ERROR)));
                } catch (IOException ex1) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return SCREENS.get(view);
    }

    public void remode(String remove) {
        SCREENS.remove(remove);
    }

    public void getDialog(String interfaces, boolean remove) {
        if (remove) {
            SCREENS.remove(interfaces);
        }
        JFXDialogLayout dl = new JFXDialogLayout();
        Node node = this.get(interfaces);
        dl.setBody(node);
        if (dialog != null) {
            dialog.close();
        }
        dialog = new JFXDialog(stackPane, dl, JFXDialog.DialogTransition.CENTER, false);
        dialog.show(stackPane);
    }

    public void setContaint(Node Contrainaire, String interfaces) {
        try {
            Node child = this.get(interfaces);
            if (Contrainaire instanceof StackPane) {
                StackPane contain_area = (StackPane) Contrainaire;
                contain_area.getChildren().removeAll();
                contain_area.getChildren().setAll(child);
            } else if (Contrainaire instanceof VBox) {
                VBox contain_area = (VBox) Contrainaire;
                contain_area.getChildren().removeAll();
                contain_area.getChildren().setAll(child);
            } else if (Contrainaire instanceof AnchorPane) {
                AnchorPane contain_area = (AnchorPane) Contrainaire;
                contain_area.getChildren().removeAll();
                contain_area.getChildren().setAll(child);
            } else if (Contrainaire instanceof ScrollPane) {
                ScrollPane contain_area = (ScrollPane) Contrainaire;
                contain_area.setContent(child);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SelectDataFor(Button... bt) {
        try {
            for (Button tr : bt) {
                tr.setStyle("-fx-background-color: #C4BEBB;-fx-text-fill: #000000;");
                tr.setFont(Font.font("System", FontPosture.REGULAR, 13));
            }
            bt[0].setStyle("-fx-background-color: #37479C;-fx-text-fill: #fff;");
            bt[0].setFont(Font.font("System", FontWeight.BOLD, 13));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
