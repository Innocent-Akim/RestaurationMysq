/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.ctrlgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.icons525.Icons525View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ISDR
 */
public class PrincipalController implements Initializable {

    @FXML
    private StackPane screen;
    @FXML
    private AnchorPane menu_security;
    @FXML
    private AnchorPane pan1;
    @FXML
    private Label b_dash;
    @FXML
    private AnchorPane pan2;
    @FXML
    private Label b_operation;
    @FXML
    private AnchorPane pan4;
    @FXML
    private Label b_rapport;
    @FXML
    private FontAwesomeIconView r;
    @FXML
    private AnchorPane pan41;
    @FXML
    private Label b_parametre;
    @FXML
    private FontAwesomeIconView p;
    @FXML
    private Text txUserName;
    @FXML
    private Text txtFonctionuser;
    @FXML
    private JFXListView<?> listdata;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
