package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load FXML
            Parent root = FXMLLoader.load(getClass().getResource("gg.fxml"));

            // Create a Scene
            Scene scene = new Scene(root);

            // Load CSS
            URL cssResource = getClass().getResource("application.css");
            if (cssResource != null) {
                scene.getStylesheets().add(cssResource.toExternalForm());
            } else {
                System.out.println("CSS file not found!");
            }


            // Set up the stage
            primaryStage.setTitle("Timer");
//            primaryStage.initStyle(StageStyle.TRANSPARENT);
            Image icon = new Image("ma.png"); // Use the path to your icon image
            primaryStage.getIcons().add(icon);

//            primaryStage.setOpacity(0.8); // 80% opaque

//            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.setScene(scene);
//            primaryStage.setTitle("JavaFX Application");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
