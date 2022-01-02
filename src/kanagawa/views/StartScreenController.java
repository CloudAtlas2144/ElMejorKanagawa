package kanagawa.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import kanagawa.Utils;
import kanagawa.models.Card;
import kanagawa.models.Game;
import kanagawa.models.Player;

import java.io.IOException;

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

    @FXML
    private AnchorPane contentWrapper;

    /**
     * Launches automatically when the window is created
     */
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

            // At the start of the game and first round, the game chooses the first player randomly
            // For the next rounds, the first player will be the one having the professor bonus
            game.chooseRandomFirstPlayer();

            // We load the main game window
            try {
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("main_game_screen.fxml"));

                Scene scene = new Scene(fxmlLoader.load(), screenBounds.getWidth(), screenBounds.getHeight());
                Stage stage = new Stage();
                stage.setTitle("Kanagawa");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setMaximized(true);
                stage.show();
                Utils.closeWindow(event); // Close start window
            } catch (IOException e) {
                System.out.println("Impossible de créer la fenêtre !");
                e.printStackTrace();
            }
        }

    }
}
