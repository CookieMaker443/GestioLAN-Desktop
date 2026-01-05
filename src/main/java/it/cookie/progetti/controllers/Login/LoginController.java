package it.cookie.progetti.controllers.Login;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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

    @FXML
    private Button ChangeServerButton;
;
    
    @FXML
    private void handleLoginButtonAction() throws IOException {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            System.out.println("Username or Password is empty!");
            showAlert(bundle.getString("login.Warning"), bundle.getString("login.MISSING_FIELDS"), AlertType.WARNING);
            return;
        }
        String username = usernameField.getText();
        String password = passwordField.getText();
    
    }

    @FXML
    private void handleChangeServerButton(ActionEvent event) throws IOException {
        try {
        // Carica la vista del ServerConfigView
        root = FXMLLoader.load(getClass().getResource("/FXML/Login/Settings/ServerConfigView.fxml"), bundle);
        //scene = new Scene(root);
        //PrimaryStage.setScene(scene);
        //PrimaryStage.show();

        // Crea un nuovo stage
        Stage popupStage = new Stage();

        //inizializza lo stage in modalità popup
        popupStage.initModality(Modality.WINDOW_MODAL);

        // recupera lo stage (di partenza) dal bottone cliccato
        Stage starterStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        popupStage.initOwner(starterStage);

        popupStage.setMinWidth(256);  // Impedisce alla finestra di stringersi troppo
        popupStage.setMinHeight(128); // Impedisce alla finestra di abbassarsi troppo

        popupStage.setTitle(bundle.getString("login.ChangeServer"));
        Scene scene = new Scene(root, 256,128);

        popupStage.setScene(scene);
        popupStage.showAndWait(); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void switchToMainMenu() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/FXML/MainMenu.fxml"), bundle);
        stage = (Stage)loginButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
}
