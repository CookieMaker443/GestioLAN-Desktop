package it.cookie.progetti;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.Button;
// import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import java.util.Locale;


public class App extends Application implements EventHandler<ActionEvent>
{
    Button button;

    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale locale = Locale.forLanguageTag("it-IT");
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.language", locale);
        button = new Button(bundle.getString("button.login"));
        button.setOnAction(e -> System.out.println("Hello, JavaFX!"));

        //StackPane plane = new StackPane();
        //plane.getChildren().add(button);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/LoginView.fxml"), bundle);
        Parent root = loader.load(); // Carica l'interfaccia dal file FXML

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle(bundle.getString("app.title"));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Button clicked!");
        
    }
}
