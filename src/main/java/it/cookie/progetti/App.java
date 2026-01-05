package it.cookie.progetti;


import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application implements EventHandler<ActionEvent>
{
    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale locale = Locale.forLanguageTag("it-IT");
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.language", locale);

        //button = new Button(bundle.getString("button.login"));
        //button.setOnAction(e -> System.out.println("Hello, JavaFX!"));
        //StackPane plane = new StackPane();
        //plane.getChildren().add(button);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login/LoginView.fxml"), bundle);
        Parent root = loader.load(); // Carica l'interfaccia dal file FXML

        final double MIN_WIDTH = 768;
        final double MIN_HEIGHT = 544;
        
        // 1. Imposta la dimensione iniziale della Scene
        Scene scene = new Scene(root, MIN_WIDTH, MIN_HEIGHT); 

        // 2. Imposta i limiti minimi allo Stage (la finestra)
        primaryStage.setMinWidth(MIN_WIDTH); 
        primaryStage.setMinHeight(MIN_HEIGHT);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle(bundle.getString("app.title"));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Button clicked!");
        
    }
}
