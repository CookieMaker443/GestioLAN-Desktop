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
    public void initialize() {
        System.out.println("LoginController inizializzato: collego i componenti...");
        
        // Colleghiamo la rete al SessionManager
        userNetController.Attach(SessionManager.getInstance());
    }

    @FXML
    @SuppressWarnings("unused")
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

        // appena  il SessionManager e pronto, chiama questo update()
        SessionManager.getInstance().Attach(this);  

        // passa la richiesta di login al UserNetController ed esegui il login
        userNetController.UserLogin(username, password); 
    }

    // possibile refactor
    @Override
    public void Update(Subject subject, Object state) {
        javafx.application.Platform.runLater(() -> {
            // Disabilitato l'ascolto per evitare doppie chiamate istantanee
            System.out.println("LoginController ha ricevuto Update dal SessionManager.");
            SessionManager.getInstance().Detach(this);
            loginButton.setDisable(false);

            if (state instanceof user) {
               try {
                   switchToMainMenu();
                } catch (IOException e) {
                    // e.printStackTrace();
                    System.out.println("Forse errore di connessione?");
                }
            } else if (state instanceof String errorKey){
                // Qui state è una stringa di errore del bundle. 
                showAlert(bundle.getString("login.Error"), bundle.getString(errorKey), AlertType.ERROR);
            } else {
                // Caso generico se state fosse null
                showAlert(bundle.getString("login.Error"), bundle.getString("login.CONNECTION_ERROR"), AlertType.ERROR);
            }
        });
    }

    @FXML
    @SuppressWarnings("unused")
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
        Scene changeServerScene = new Scene(root, 256,128);

        popupStage.setScene(changeServerScene);

        // Centra la finestra popup rispetto alla finestra di partenza
        double centerX = starterStage.getX() + starterStage.getWidth() / 2;
        double centerY = starterStage.getY() + starterStage.getHeight() / 2;

        popupStage.setX(centerX - 512.0 / 2);
        popupStage.setY(centerY - 381.0 / 2);

        popupStage.showAndWait();

        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Errore nel caricamento della finestra di configurazione server.");
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
        userNetController.DetachAll(); // Evita di tenere Observer inutili
    } 
}
