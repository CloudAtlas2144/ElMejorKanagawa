package kanagawa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kanagawa.models.Game;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game.getGameInstance();

        Parent root = FXMLLoader.load(getClass().getResource("views/start_screen.fxml"));
        primaryStage.setTitle("Kanagawa");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
