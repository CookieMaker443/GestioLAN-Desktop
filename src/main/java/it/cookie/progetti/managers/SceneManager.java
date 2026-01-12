package it.cookie.progetti.managers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SceneManager {
    private static SceneManager istance;
    private Map<String, String> FXML_SCENES = new HashMap<>();
    Locale locale = Locale.forLanguageTag("it-IT");
    ResourceBundle langBundle = ResourceBundle.getBundle("i18n.language", Locale.getDefault());
    ResourceBundle sceneBundle;
    FXMLLoader loader;

    public enum SceneKeys {
        LOGIN_VIEW("LoginView"),
        MAIN_MENU_VIEW("MainMenuView"),
        SERVER_CONFIG_POPUP("ServerConfigView");

        private final String key;

        SceneKeys(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    public static SceneManager getInstance() {
        if (istance == null) {
            istance = new SceneManager();
        }
        return istance;
    }

    private SceneManager() {
        InitializeSceneFile();
    }

    private void InitializeSceneFile() {
        sceneBundle = ResourceBundle.getBundle("scenes.scenes");
        for (String key : sceneBundle.keySet()) {
            FXML_SCENES.put(key, sceneBundle.getString(key));
        }
        FXML_SCENES = Collections.unmodifiableMap(FXML_SCENES);
    }

    public void loadScene(Stage stage, SceneKeys sceneKey, String title, double minWidth, double minHeight) {
        try {
            // prende la scena passarta come parametro e la carica
            loader = new FXMLLoader(getClass().getResource(FXML_SCENES.get(sceneKey.getKey())), langBundle);
            
            // Carica il file FXML
            Parent root = loader.load();
            Scene scene = new Scene(root, minWidth, minHeight);

            // Setta le dimensioni minime
            stage.setMinWidth(minWidth);
            stage.setMinHeight(minHeight);

            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            // e.printStackTrace();
            showAlert("Error", "Unable to load the scene: " + sceneKey.getKey(), AlertType.ERROR);
            System.out.println("Errore nel caricamento della scena: " + sceneKey.getKey());
        }
    }

    public void loadPopupScene(ActionEvent event, String fxmlPath, String title, double minWidth, double minHeight) {
        // Implementazione del caricamento della scena popup
    }

    public  void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}