package kanagawa.views;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import kanagawa.Utils;
import kanagawa.models.Game;
import kanagawa.models.Player;

public class StartScreenController {
    private Game game;

    @FXML
    private TextField tf_player1;

    @FXML
    private TextField tf_player2;

    @FXML
    private TextField tf_player3;

    @FXML
    private TextField tf_player4;

    @FXML
    private Label error_label;

    public void initialize() {
        game = Game.getGameInstance();
    }

    @FXML
    private BorderPane borderPane;

    @FXML
    public void onExitButtonClicked(MouseEvent event) {
        Utils.closeWindow(event);
    }

    @FXML
    public void onPlayButtonClicked(MouseEvent event) {
        // Errors handling
        if (tf_player1.getText().isEmpty()) {
            error_label.setText("Renseigner un premier joueur");
        } else if (!tf_player1.getText().isEmpty() && tf_player2.getText().isEmpty()) {
            error_label.setText("Il faut au moins deux joueurs");
        } else {
            Player player1 = new Player(tf_player1.getText());
            Player player2 = new Player(tf_player2.getText());
            Player player3 = null;
            Player player4 = null;

            if (!tf_player3.getText().isEmpty()) {
                player3 = new Player(tf_player3.getText());
            }

            if (!tf_player4.getText().isEmpty()) {
                player4 = new Player(tf_player4.getText());
            }

            game.addPlayers(player1, player2, player3, player4);

            // We load the main game window
            try {
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("main_game_screen.fxml"));

                Scene scene = new Scene(fxmlLoader.load(), screenBounds.getMaxX(), screenBounds.getMaxY());
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
}
