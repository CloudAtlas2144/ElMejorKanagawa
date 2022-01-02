package kanagawa.views;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import kanagawa.Utils;
import kanagawa.models.Game;
import kanagawa.models.Player;

import java.util.ArrayList;

public class MainGameController {

    private Game game;

    @FXML
    private VBox playersList;

    @FXML
    private Pane one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve;

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



    public void initialize() {
        game = Game.getGameInstance();

        // Set root size to match current window size
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root.setPrefWidth(screenBounds.getWidth());
        root.setPrefHeight(screenBounds.getHeight());

        createCardOnBoard();
        createPlayers(game.getPlayers());
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

    }
}
