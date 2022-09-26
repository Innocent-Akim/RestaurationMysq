/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrload;

import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lib.app.Datasource;
import lib.ctrlgui.UtilisateurController;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class LoadTacheController implements Initializable {

    @FXML
    private Label taske;
    public static Label taskpublic;

    public static String taskpublc, public_etat, public_code;
    @FXML
    private JFXCheckBox chckbnt;
    public static String taskeString;
    @FXML
    private Label etat;
    @FXML
    private Label code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        taske.setText(taskeString);
        etat.setText(public_etat);
        code.setText(public_code);
//        LoadUtilisateurController.public_codeLabel = code;
        if (etat.getText().equals("0")) {
            chckbnt.setSelected(false);
        } else {
            chckbnt.setSelected(true);
        }
        taske.setText(taskpublc);
        taskpublic = taske;
        chckbnt.setOnAction((e) -> {
            String exist = Datasource.getValue("SELECT status FROM tache WHERE designation='" + taske.getText() + "' AND codeUser='" + UtilisateurController.codeUserLabel.getText() + "'");
            if (exist != null) {
                Datasource.execute("UPDATE `tache` SET  `status`=? WHERE `designation`=? AND `codeUser`=?", "1".equals(exist) ? "0" : "1", taske.getText(), UtilisateurController.codeUserLabel.getText());
            } else {
                Datasource.execute("INSERT INTO `tache` SET `designation`=?, `codeUser`=?, `status`=?", taske.getText(), UtilisateurController.codeUserLabel.getText(), "1");
            }
        });
    }

}
