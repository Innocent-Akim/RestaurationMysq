/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.imprimer;

import CallWindow.Callwindow;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import lib.app.Alerte;
import lib.app.Datasource;
import lib.app.Files;
import lib.app.Neurohub;
import lib.app.manifest;
import lib.ctrlgui.PrincipalController;
import static lib.dbconnect.Dbconnect.cnx;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ISDR
 */
public class Imprimer {

    public void imprimer(String requete, Map parametres, String cheminJasper) {
//        System.err.println(requete);

        try {
            JasperDesign jaspertDisign = JRXmlLoader.load(Files.CreadFolder(null) + cheminJasper + ".jrxml");
            JRDesignQuery gn = new JRDesignQuery();
            gn.setText(requete);
            jaspertDisign.setQuery(gn);
            JasperReport f1 = JasperCompileManager.compileReport(jaspertDisign);
            JasperPrint a = JasperFillManager.fillReport(f1, parametres, cnx());

            JasperViewer.viewReport(a, false);
            JasperViewer view = new JasperViewer(a, true);
            InputStream stram = getClass().getResourceAsStream(manifest.RAPPORT_LOGO);
            view.setResizable(true);
            ImageIcon icon = new ImageIcon(ImageIO.read(stram));
            view.setIconImage(icon.getImage());
            view.show();

        } catch (JRException ex) {
            Files.error("Erreur d'impression : >>" + requete + " --> " + ex.getMessage());
            Alerte.alerte.setAlert("Aucune donnée n'as été trouvée !" + ex, Alert.AlertType.ERROR);
        } catch (IOException ex) {
            Files.error("Erreur d'impression : >>" + requete + " --> " + ex.getMessage());

        }
    }

    public boolean isPrint(String requete, String fichier, Map parametres, Boolean Ouvrir_fenetre) {
        System.out.println(requete);
        JasperPrint a = null;
        try {
            System.out.println(manifest.RAPPORT_LOGO);
            if (Datasource.getCountBy(requete) > 0) {
                JasperDesign jaspertDisign = JRXmlLoader.load(Files.CreadFolder(null) + fichier + ".jrxml");
                JRDesignQuery gn = new JRDesignQuery();
                gn.setText(requete);
                jaspertDisign.setQuery(gn);
                JasperReport Fichier = JasperCompileManager.compileReport(jaspertDisign);
                a = JasperFillManager.fillReport(Fichier, parametres, cnx());
                JasperPrintManager.printReport(a, Ouvrir_fenetre);
                JasperViewer view = new JasperViewer(a, false);
                view.setTitle("REA-PATISSERIE");
                view.setResizable(true);
                InputStream stram = getClass().getResourceAsStream(manifest.RAPPORT_LOGO);
                ImageIcon icon = new ImageIcon(ImageIO.read(stram));
                view.setIconImage(icon.getImage());
                view.show();
                return true;
            } else {
                Alerte.alerte.setAlert("Aucune donnée n'as été trouvée !", Alert.AlertType.ERROR);
                return false;
            }
        } catch (JRException ex) {
            Files.error("Erreur d'impression : >>" + requete + " --> " + ex.getMessage());
            Alerte.alerte.setAlert("Aucune donnée n'as été trouvée !" + ex, Alert.AlertType.ERROR);
        } catch (IOException ex) {
            Files.error("Erreur d'impression : >>" + requete + " --> " + ex.getMessage());
        }
        return false;
    }
}
