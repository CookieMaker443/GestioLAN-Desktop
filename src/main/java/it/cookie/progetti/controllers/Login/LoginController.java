package it.cookie.progetti.controllers.Login;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    Locale locale = Locale.forLanguageTag("it-IT");
    ResourceBundle bundle = ResourceBundle.getBundle("i18n.language", locale);

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private String userTest = "admin";
    private String passTest = "admin";
    

    @FXML
    private void handleLoginButtonAction() throws IOException {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            System.out.println("Username or Password is empty!");
            showAlert(bundle.getString("login.Warning"), bundle.getString("login.MISSING_FIELDS"), AlertType.WARNING);
            return;
        }
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        
        /*else {
            System.out.println("Attempting login with Username: " + usernameField.getText() + " and Password: " + passwordField.getText());
            if(usernameField.getText().equals(userTest) && passwordField.getText().equals(passTest)) {
                System.out.println("Login successful!");
                //showAlert(bundle.getString("login.Success"), bundle.getString("login.SUCESS"), AlertType.INFORMATION);
                switchToMainMenu();
            } else {
                // System.out.println("Login failed!");
                showAlert(bundle.getString("login.Error"), bundle.getString("login.FAIL"), AlertType.ERROR);
                
            }
        }*/
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void switchToMainMenu() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/it/cookie/progetti/recources/FXML/MainMenu.fxml"), bundle);
        stage = (Stage)loginButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
}
