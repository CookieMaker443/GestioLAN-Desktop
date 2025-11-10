package it.cookie.progetti.ui;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
    Locale locale = Locale.forLanguageTag("it-IT");
    ResourceBundle bundle = ResourceBundle.getBundle("i18n.language", locale);

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private String userTest = "admin";
    private String passTest = "admin";
    

    @FXML
    private void handleLoginButtonAction() {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            System.out.println("Username or Password is empty!");
            showAlert(bundle.getString("login.Warning"), bundle.getString("login.MISSING_FIELDS"), AlertType.WARNING);
        } else {
            System.out.println("Attempting login with Username: " + usernameField.getText() + " and Password: " + passwordField.getText());
            if(usernameField.getText().equals(userTest) && passwordField.getText().equals(passTest)) {
                System.out.println("Login successful!");
                showAlert(bundle.getString("login.Success"), bundle.getString("login.SUCESS"), AlertType.INFORMATION);
            } else {
                System.out.println("Login failed!");
                showAlert(bundle.getString("login.Error"), bundle.getString("login.FAIL"), AlertType.ERROR);
            }
        }
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
