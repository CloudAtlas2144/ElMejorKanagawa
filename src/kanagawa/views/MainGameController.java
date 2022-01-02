package kanagawa.views;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import kanagawa.Utils;
import kanagawa.models.Game;
import kanagawa.models.Player;

import java.util.ArrayList;
import java.util.Objects;

public class MainGameController {

    private Game game;

    @FXML
    private VBox playersList;

    @FXML
    private Pane one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve;

    @FXML
    private Label currentPlayerUsername;

    @FXML
    private Label creditCount, penCount, mathCount, infoCount, energyCount, industryCount, ergoCount, mechaCount, managementCount, LanguageCount;

    @FXML
    private Button firstColumnButton, secondColumnButton, thirdColumnButton, fourthColumnButton;

    @FXML
    private Button nextPlayerButton;

    @FXML
    private Button quitGameButton;

    @FXML
    private HBox cardsList;

    @FXML
    private BorderPane root;



    public void initialize() throws InterruptedException {
        game = Game.getGameInstance();

        // Set root size to match current window size
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root.setPrefWidth(screenBounds.getWidth());
        root.setPrefHeight(screenBounds.getHeight());

        createCardOnBoard();
        createPlayers(game.getPlayers());

        currentPlayerUsername.setText(game.getCurrentRound().getCurrentPlayer().getUsername());
    }

    @FXML
    public void onQuitGameButtonClicked(MouseEvent event) {
        Utils.closeWindow(event);
    }

    @FXML
    public void onNextPlayerButtonClicked(MouseEvent event) {

    }

    @FXML
    public void onFirstColumnButtonClicked(MouseEvent event) {}

    @FXML
    public void onSecondColumnButtonClicked(MouseEvent event) {}

    @FXML
    public void onThirdColumnButtonClicked(MouseEvent event) {}

    @FXML
    public void onFourthColumnButtonClicked(MouseEvent event) {}

    private void createCardOnBoard() {

    }

    private void createPlayers(ArrayList<Player> players) {
        for (Player player : game.getPlayers()) {
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefWidth(199);
            anchorPane.setPrefHeight(87);
            anchorPane.setStyle("-fx-background-color: white; -fx-border-color: black");

            Label label = new Label(player.getUsername());
            label.setLayoutX(14);
            label.setLayoutY(32);

            label.setFont(new Font("Verdana", 18));

            anchorPane.getChildren().add(label);

            if (player.isFirstPlayer()) {
                ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/medal.png"))));

                imageView.setFitWidth(18);
                imageView.setFitHeight(18);

                imageView.setLayoutX(166);
                imageView.setLayoutY(8);

                anchorPane.getChildren().add(imageView);
            }

            if (player.getInventory().isHasProfessor()) {
                ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/professor.png"))));

                imageView.setFitWidth(18);
                imageView.setFitHeight(18);

                imageView.setLayoutX(166);
                imageView.setLayoutY(34);

                anchorPane.getChildren().add(imageView);
            }

            if (player.isPlaying()) {
                ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/game-controller.png"))));

                imageView.setFitWidth(18);
                imageView.setFitHeight(18);

                imageView.setLayoutX(166);
                imageView.setLayoutY(61);

                anchorPane.getChildren().add(imageView);
            }

            playersList.getChildren().add(anchorPane);
        }
    }
}
