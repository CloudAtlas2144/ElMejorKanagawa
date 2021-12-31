package kanagawa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import kanagawa.models.Game;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game.getGameInstance();

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        Parent root = FXMLLoader.load(getClass().getResource("views/start_screen.fxml"));
        primaryStage.setTitle("Kanagawa");
        primaryStage.setScene(new Scene(root, screenBounds.getMaxX(), screenBounds.getMaxY()));
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
