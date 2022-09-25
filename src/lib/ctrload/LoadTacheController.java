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

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class LoadTacheController implements Initializable {

    @FXML
    private Label taske;
    public static Label taskpublic;

    public static String taskpublc;
    @FXML
    private JFXCheckBox chckbnt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        taske.setText(taskpublc);
        taskpublic = taske;
        chckbnt.setOnAction((e) -> {
         Datasource.execute("INSERT INTO `tache` SET `designation`=?, `codeUser`=?, `status`=?",taske.getText());
//            Datasource.execute("UPDATE `tache` SET `status`=? WHERE designation=? AND codeUser=?",taske.getText());

        });
    }

}
