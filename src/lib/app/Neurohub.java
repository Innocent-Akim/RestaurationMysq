/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.app;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ISDR
 */
public class Neurohub {

    public static boolean isFieldsempty(Node... field) {
        int i = 0;
        while (i < field.length) {
            if (field[i] instanceof TextField) {
                TextField text = (TextField) field[i];
                if (text.getText() == null || text.getText().isEmpty()) {
                    break;
                }
                i++;
            } else if (field[i] instanceof PasswordField) {
                PasswordField pass = (PasswordField) field[i];
                if (pass.getText() == null || pass.getText().isEmpty()) {
                    break;
                }
                i++;
            } else if (field[i] instanceof ComboBox) {
                ComboBox comboBox = (ComboBox) field[i];
                if (comboBox.getValue() == null) {
                    break;
                }
                i++;
            } else if (field[i] instanceof DatePicker) {
                DatePicker date = (DatePicker) field[i];
                if (date.getValue() == null) {
                    break;
                }
                i++;
            } else if (field[i] instanceof TextArea) {
                TextArea area = (TextArea) field[i];
                if (area.getText().trim().isEmpty()) {
                    break;
                }
                i++;
            } else if (field[i] instanceof Label) {
                Label txt = (Label) field[i];
                if (txt.getText() == null || txt.getText().isEmpty()) {
                    break;
                }
                i++;
            } else if (field[i] instanceof Text) {
                Text txt = (Text) field[i];
                if (txt.getText() == null) {
                    break;
                }
                i++;
            }
        }
        return i != field.length;
    }

    public static void initFields(Node... field) {
        for (Node f : field) {
            if (f instanceof TextField) {
                TextField text = (TextField) f;
                text.setText(null);
            } else if (f instanceof DatePicker) {
                DatePicker text = (DatePicker) f;
                text.setValue(null);
            } else if (f instanceof TextArea) {
                TextArea text = (TextArea) f;
                text.setText(null);
            } else if (f instanceof ComboBox) {
                ComboBox text = (ComboBox) f;
                text.setValue(null);
            } else if (f instanceof Label) {
                Label txt = (Label) f;
                txt.setText(null);
            } else if (f instanceof Text) {
                Text txt = (Text) f;
                txt.setText(null);
            } else if (f instanceof PasswordField) {
                PasswordField txt = (PasswordField) f;
                txt.setText(null);
            } else if (f instanceof JFXComboBox) {
                JFXComboBox txt = (JFXComboBox) f;
                txt.setValue(null);
            }

        }
    }

    public void initFieldClean(TextField... node) {
        for (TextField text : node) {
            text.setEditable(false);
        }

    }

    public void initFieldActive(TextField... node) {
        for (TextField text : node) {
            text.setStyle("-fx-border-color: #c2c2c2c2; -fx-background-color: Transparent; -fx-border-width:  0.5px;");
            text.setEditable(true);
        }
    }

    public static void setSelect(CheckBox... btn) {
        for (CheckBox rd : btn) {
            rd.setSelected(false);
        }
        btn[0].setSelected(true);
    }

    public static void setSelect(RadioButton... btn) {
        for (RadioButton rd : btn) {
            rd.setSelected(false);
        }
        btn[0].setSelected(true);
    }

    public void initArray(ArrayList... Array) {
        for (ArrayList liste : Array) {
            liste.clear();
        }
    }

    public static void setImage(Circle cir2, String lien) {
        Image img = null;
        File f = new File(lien);
        System.out.println(lien);
        if (f.exists() && !f.isDirectory()) {
            img = new Image(lien, false);
        } else {
//            img = new Image(manifest.APP_DEFAULT_IMG, false);
        }
        try {
            cir2.setStroke(Color.SEAGREEN);
            cir2.setFill(new ImagePattern(img));
            cir2.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        } catch (Exception e) {
            System.out.print(e);
        }
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

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String NombreToString(float nombre, String devise) {
        BigDecimal num = new BigDecimal(nombre);
        NumberFormat formatter
                = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
        String result = (formatter.format(num));
        return Premier_Maj(result) + " " + devise;

    }

    public static String Premier_Maj(String str) {
        String _fst = ("" + str.charAt(0)).toUpperCase();
        String scnd = ("" + str.substring(1, str.length())).toLowerCase();
        str = _fst + scnd;
        return str;
    }

    public static LocalDate toLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();

    }

    public String createFacture(String name) {
        try {
            String fact = Datasource.getValue("SELECT COALESCE(MAX(code),0)+1 FROM entetefacture WHERE refEntreprise='" + Datasource.refEntreprise + "'");
            String codeClient = Datasource.getValue("SELECT code FROM personne WHERE type='CLIENTS' AND refEntreprise='" + Datasource.refEntreprise + "' AND nom='" + name + "'");
            boolean status = Datasource.execute("INSERT INTO `entetefacture` SET `code`=?,`montant`=?,`codeClient`=?,`codeAgent`=?,`refEntreprise`=?", fact, "0", codeClient, Vars.vars.getCode(), Datasource.refEntreprise);
            if (status) {
                return Datasource.getValue("SELECT MAX(code) FROM `entetefacture` WHERE refEntreprise='" + Datasource.refEntreprise + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Neurohub neurohub = new Neurohub();
}
