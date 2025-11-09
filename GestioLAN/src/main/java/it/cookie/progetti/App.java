package it.cookie.progetti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application
{
    Button button;

    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        button = new Button("Click Me");
        button.setOnAction(e -> System.out.println("Hello, JavaFX!"));

        StackPane plane = new StackPane();
        plane.getChildren().add(button);

        Scene scene = new Scene(plane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GestioLAN Client");
        primaryStage.show();
    }

}
