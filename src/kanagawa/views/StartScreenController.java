package kanagawa.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kanagawa.Utils;

import java.io.IOException;

public class StartScreenController {
    @FXML
    private BorderPane borderPane;

    @FXML
    public void onExitButtonClicked(MouseEvent event) {
        Utils.closeWindow(event);
    }

    @FXML
    public void onPlayButtonClicked(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("main_game_screen.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            Stage stage = new Stage();
            stage.setTitle("Kanagawa");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setFullScreen(true);
            stage.show();
            Utils.closeWindow(event); // Close start window
        } catch (IOException e) {
            System.out.println("Impossible de créer la fenêtre !");
        }
    }
}
