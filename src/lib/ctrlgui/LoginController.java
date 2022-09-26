/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.gn.GNAvatarView;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lib.Main.Acces;
import lib.Main.Main;
import static lib.Main.Main.stage;
import lib.Main.View;
import lib.app.Alerte;
import lib.app.Datasource;
import lib.app.Msg;
import lib.app.Vars;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class LoginController implements Initializable {

    @FXML
    private StackPane fenetreLogin;
    @FXML
    private GNAvatarView avatar;
    @FXML
    private HBox box_username;
    @FXML
    private TextField usernameTfd;
    @FXML
    private HBox box_password;
    @FXML
    private PasswordField passwordTfd;
    @FXML
    private Button btnConnecter;
    @FXML
    private ImageView loadIcon;
    @FXML
    private Text message;
    @FXML
    private Label rester_essaie;
    @FXML
    private Label lbl_error;
    @FXML
    private Label lbl_username;
    @FXML
    private Label lbl_password;
    @FXML
    private Hyperlink btn_configuration;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initEvent();
    }

    void initEvent() {
        btnConnecter.setOnAction((event) -> {
            ResultSet res = Datasource.getrResultat("SELECT * FROM vs_login WHERE username='" + usernameTfd.getText() + "' AND password=md5('" + passwordTfd.getText() + "')");
            try {
                if (res.next()) {
                    Vars.vars.setCode(res.getString("ID"));
                    Vars.vars.setRefEntreprise(res.getString("refEntreprise"));
                    Vars.vars.setNom(res.getString("nom"));
                    Vars.vars.setContact(res.getString("contact"));
                    Main.acces = new Acces(res.getString("ID"));
                    usernameTfd.clear();
                    passwordTfd.clear();
                    stage.setContent(View.instance().get(View.MENU));

                } else {
                    Alerte.alerteErreur("Attention", Msg.MESSAGE_ERREUR_CONNEXION);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    private void switchCreate(MouseEvent event) {
    }

}
