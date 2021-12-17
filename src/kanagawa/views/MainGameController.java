package kanagawa.views;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import kanagawa.Utils;

public class MainGameController {

    @FXML
    private HBox hbox;

    @FXML
    private Pane UnDeux;

    @FXML
    private Button nextRoundButton;

    public void initialize() {
        createCardOnBoard();

        // Initialiser cartes du joueur dans la VBOX
        for (int i=0; i<10; ++i){
            SplitPane splitPane = new SplitPane();
            splitPane.setMinWidth(400);
            splitPane.setMinHeight(150);
            splitPane.setMaxHeight(360);
            AnchorPane skillsPane = new AnchorPane();
            AnchorPane UVPane = new AnchorPane();
            splitPane.getItems().addAll(skillsPane, UVPane);
            splitPane.setDividerPositions(0.32);
            splitPane.setStyle("-fx-background-color:lightgreen; -fx-border-color:black;");
            hbox.getChildren().add(splitPane);
        }
    }

    @FXML
    public void onQuitGameButtonClicked(MouseEvent event) {
        Utils.closeWindow(event);
    }

    @FXML
    public void onNextRoundButtonClicked(MouseEvent event) {
        SplitPane n = (SplitPane) UnDeux.getChildren().get(0);
        AnchorPane a = (AnchorPane) n.getItems().get(0);
        VBox v = (VBox) a.getChildren().get(0);
        v.getChildren().add(new Label("Bonjour"));
    }

    private void createCardOnBoard() {
        // Initialiser les cartes sur le plateau
        SplitPane splitPaneBoard = new SplitPane();
        splitPaneBoard.setMinWidth(277);
        splitPaneBoard.setMinHeight(194);
        AnchorPane skillsPaneBoard = new AnchorPane();
        skillsPaneBoard.getChildren().add(new VBox());
        AnchorPane UVPaneBoard = new AnchorPane();
        UVPaneBoard.getChildren().add(new VBox());
        splitPaneBoard.getItems().addAll(skillsPaneBoard, UVPaneBoard);
        splitPaneBoard.setDividerPositions(0.298);

        UnDeux.getChildren().add(splitPaneBoard);
    }
}
