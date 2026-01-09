package it.cookie.progetti.controllers.Login;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import it.cookie.utils.interfaces.observer.Observer;
import it.cookie.utils.interfaces.observer.Subject;
import it.cookie.utils.network.managers.SessionManager;
import it.cookie.utils.network.user.UserNetController;
import it.cookie.utils.network.user.user;
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

public class LoginController implements Observer{
    Locale locale = Locale.forLanguageTag("it-IT");
    ResourceBundle bundle = ResourceBundle.getBundle("i18n.language", locale);
    UserNetController userNetController = new UserNetController();

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
        // disabilita il bottone per evitare spam
        loginButton.setDisable(true);

        String username = usernameField.getText();
        String password = passwordField.getText();
        
        System.out.println("Attempting login with Username: " + username);

        // passa la richiesta di login al UserNetController
        userNetController.Attach(SessionManager.getInstance());
        // appena  il SessionManager e pronto, chiama questo update()
        SessionManager.getInstance().Attach(this);  

        // Esegui il login
        userNetController.UserLogin(username, password); 
    }

    // possibile refactor
    @Override
    public void Update(Subject subject, Object state) {
        javafx.application.Platform.runLater(() -> {
            // Disabilitato l'ascolto per evitare doppie chiamate istantanee
            SessionManager.getInstance().Detach(this);
            loginButton.setDisable(false);

            if (state instanceof user u) {
               try {
                   switchToMainMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Qui state è null. 
                // TODO:  passare un messaggio d'errore dal NetController
                showAlert(bundle.getString("login.Error"), bundle.getString("login.INVALID_CREDENTIALS"), AlertType.ERROR);
            }
        });
    }

    // Dare unocchiata
    /*
    @Override
    public void Update(Subject subject, Object state) {
        if(state instanceof user u) { // se cel'user, connessione riuscita
            // Platform.runLater sposta l'esecuzione dal thread worker al thread JavaFX
            javafx.application.Platform.runLater(() -> {
                try {
                    // se il SessionManager è stato aggiornato con un user valido, procedi
                    if (SessionManager.getInstance().IsUserLogged()) {
                        switchToMainMenu();
                        // Rimozione dell'Observer per evitare aggiornamenti futuri e memory leak
                        SessionManager.getInstance().Detach(this); 
                    } else {
                        showAlert(bundle.getString("login.Error"), bundle.getString("login.CONNECTION_ERROR"), AlertType.ERROR);
                        // Rimozione dell'Observer anche qui per evitare di venir "aggiunto" piu volte se spam clicchi il login ed evitare memory leak
                        SessionManager.getInstance().Detach(this); 
                        loginButton.setDisable(false);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else { // probabile errore di connessione
            javafx.application.Platform.runLater(() -> {
                showAlert(bundle.getString("login.Error"), bundle.getString("login.CONNECTION_ERROR"), AlertType.ERROR);
                // Rimozione dell'Observer anche qui per evitare di venir "aggiunto" piu volte se spam clicchi il login ed evitare memory leak
                SessionManager.getInstance().Detach(this); 
                loginButton.setDisable(false);
            });
        }  
    } */

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

        popupStage.setMinWidth(512);  // Impedisce alla finestra di stringersi troppo
        popupStage.setMinHeight(381); // Impedisce alla finestra di abbassarsi troppo

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
        root = FXMLLoader.load(getClass().getResource("/FXML/MainMenu/MainMenu.fxml"), bundle);
        stage = (Stage)loginButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
}
