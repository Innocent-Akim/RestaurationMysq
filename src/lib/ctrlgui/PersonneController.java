/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class PersonneController implements Initializable {

    @FXML
    private JFXListView<?> listView;
    @FXML
    private JFXButton client;
    @FXML
    private JFXButton agent;
    @FXML
    private TextField nom;
    @FXML
    private TextField contact;
    @FXML
    private TextField adresse;
    @FXML
    private JFXButton btaction;
    @FXML
    private Label idUpdate;
    @FXML
    private Text etiquette;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    
}
