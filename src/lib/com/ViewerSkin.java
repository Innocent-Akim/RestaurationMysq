/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.com;

import static com.sun.javafx.scene.control.skin.TextFieldSkin.BULLET;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Akim
 */
public class ViewerSkin extends SkinAction {

    private boolean shouldMaskText = true;

    public ViewerSkin(PasswordField textField) {
        super(textField);
    }

    @Override
    void mouseReleased() {
        TextField textField = getSkinnable();
        textField.setText(textField.getText());
        textField.end();
    }

    @Override
    void textChanged() {
        if (!getPasswordField().isFocused() && getPasswordField().getText() == null) {
            return;
        }
        getButton().setVisible(getPasswordField().isFocused() && !getPasswordField().getText().isEmpty());
    }

    @Override
    void focusChanged() {
        if (!getPasswordField().isFocused() && getPasswordField().getText() == null) {
            return;
        }
        getButton().setVisible(getPasswordField().isFocused() && !getPasswordField().getText().isEmpty());
    }
    boolean bool = true;

    @Override
    void mousePressed() {
        
        TextField textField = getSkinnable();
        shouldMaskText = !bool;
        textField.setText(textField.getText());
        shouldMaskText = bool;
    }

    @Override
    protected String maskText(String txt) {
        if (getSkinnable() instanceof PasswordField && shouldMaskText) {
            int n = txt.length();
            StringBuilder passwordBuilder = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                passwordBuilder.append(BULLET);
            }
            return passwordBuilder.toString();
        } else {
            return txt;
        }
    }
}
