package kanagawa.views;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.util.Callback;
import kanagawa.Utils;
import kanagawa.models.*;
import kanagawa.models.enums.Bonus;
import kanagawa.models.enums.Skill;

import java.util.*;

public class MainGameController {

    private Game game;

    @FXML
    private VBox playersList;

    @FXML
    private AnchorPane one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve;

    @FXML
    private Label currentPlayerUsername;

    @FXML
    private Label creditCount, penCount, mathCount, infoCount, energyCount, industryCount, ergoCount, mechaCount, managementCount, languageCount;

    @FXML
    private Button firstColumnButton, secondColumnButton, thirdColumnButton, fourthColumnButton;

    @FXML
    private Button nextPlayerButton;

    @FXML
    private Button quitGameButton;

    @FXML
    private HBox cardsList;



    public void initialize() throws InterruptedException {
        game = Game.getGameInstance();

        // Initialize players data

        game.shuffleCards();
        game.randomFirstCardForPlayers();
        game.getCurrentRound().setRemainingPlayers(game.getPlayers());

        game.getCurrentRound().initBoardWithPlayersCount();

        disableButtons();

        game.distributeCards();

        createPlayers(game.getPlayers());

        showPlayerData();
        showPlayerCards();
        showCardsOnBoard();
    }

    @FXML
    public void onQuitGameButtonClicked(MouseEvent event) {
        Utils.closeWindow(event);
    }

    @FXML
    public void onNextPlayerButtonClicked(MouseEvent event) {}

    @FXML
    public void onFirstColumnButtonClicked(MouseEvent event) {
        takeCardColumn(0);
    }

    @FXML
    public void onSecondColumnButtonClicked(MouseEvent event) {
        takeCardColumn(1);
    }

    @FXML
    public void onThirdColumnButtonClicked(MouseEvent event) {
        takeCardColumn(2);
    }

    @FXML
    public void onFourthColumnButtonClicked(MouseEvent event) {
        takeCardColumn(3);
    }

    private void takeCardColumn(int colIndex) {
        ArrayList<Card> firstColumnCards = game.getCurrentRound().getGameBoard()[colIndex];

        boolean deleteColumn = false;

        int columnSize = firstColumnCards.size();

        HashMap<Card, Boolean> takenCards = new HashMap<>();

        for (int i=0; i<columnSize; i++) {
            HashMap<Card, Boolean> result = createChoiceDialog(firstColumnCards);
            if (result != null) {
                deleteColumn = true;
                Map.Entry<Card, Boolean> entry = result.entrySet().iterator().next();
                if (result.get(entry.getKey())) {
                    game.getCurrentRound().getCurrentPlayer().addToPersonalWork(entry.getKey());
                    takenCards.put(entry.getKey(), true);
                } else {
                    Player currentPlayer = game.getCurrentRound().getCurrentPlayer();
                    boolean hasSkill = currentPlayer.hasSkill(entry.getKey().getUv().getSkill());
                    if (hasSkill) {
                        game.getCurrentRound().getCurrentPlayer().addToUv(entry.getKey());
                        takenCards.put(entry.getKey(), false);
                    } else {
                        Alert errorDialog = new Alert(Alert.AlertType.ERROR);
                        errorDialog.setTitle("Erreur");
                        errorDialog.setHeaderText("Impossible d'ajouter l'UV");
                        errorDialog.setContentText("Vérifier les compétences disponibles et les stylos");
                        errorDialog.showAndWait();

                        fireEventButtonEvent(colIndex);

                        deleteColumn = false;

                        break;
                    }
                }

                firstColumnCards.remove(entry.getKey());

                updateData();
            } else {
                deleteColumn = false;
                for (Map.Entry<Card, Boolean> entry : takenCards.entrySet()) {
                    if (entry.getValue()) {
                        game.getCurrentRound().getCurrentPlayer().getInventory().getPwPossessed().remove(entry.getKey().getPersonalWork());
                    } else {
                        game.getCurrentRound().getCurrentPlayer().getInventory().getUvPossessed().remove(entry.getKey().getUv());
                    }

                    firstColumnCards.add(0, entry.getKey());
                }

                break;
            }
        }

        if (deleteColumn) {
            game.getCurrentRound().removeColumn(colIndex);
            showCardsOnBoard();
            // disableAllButtons();
        }

        updateData();
    }

    private void fireEventButtonEvent(int colIndex) {
        Button button = null;

        switch (colIndex){
            case 0:
                button = firstColumnButton;
                break;
            case 1:
                button = secondColumnButton;
                break;
            case 2:
                button = thirdColumnButton;
                break;
            case 3:
                button = fourthColumnButton;
                break;
            default:
                break;
        }

        double buttonX = button.getWidth()/2;
        double buttonY = button.getHeight()/2;

        Point2D screenCoords = button.localToScreen(buttonX, buttonX);
        Point2D sceneCoords = button.localToScene(buttonX, buttonY);

        button.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, sceneCoords.getX(), sceneCoords.getY(), screenCoords.getX(),
                screenCoords.getY(), MouseButton.PRIMARY, 1, true,true, true, true, true, true, true, true, true, true, null));
    }

    private HashMap<Card, Boolean> createChoiceDialog(ArrayList<Card> data) {
        ChoiceDialog dialog = new ChoiceDialog(data.get(0), data);
        dialog.setTitle("Faites votre choix !");
        dialog.setHeaderText("Faites votre choix !");
        dialog.getDialogPane().getButtonTypes().remove(0);

        ButtonType travailPersonelNoPen = new ButtonType("Travail Perso " + "\n" + "(Sans stylo)", ButtonBar.ButtonData.OK_DONE);
        ButtonType travailPersonelPen = new ButtonType("Travail Perso " + "\n" + "(Avec stylo)", ButtonBar.ButtonData.OK_DONE);
        ButtonType UV = new ButtonType("UV", ButtonBar.ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().addAll(travailPersonelPen,travailPersonelNoPen, UV);

        dialog.setResultConverter(new Callback<ButtonType, HashMap<Card, Boolean>>() {
            @Override
            public HashMap<Card, Boolean> call(ButtonType b) {
                if (b == travailPersonelNoPen) {
                    HashMap<Card, Boolean> res = new HashMap<>();
                    res.put((Card) dialog.getSelectedItem(), true);
                    updateData();
                    return res;
                }

                if (b == travailPersonelPen) {
                    HashMap<Card, Boolean> res = new HashMap<>();
                    Card selectedCard = (Card) dialog.getSelectedItem();
                    if (game.getCurrentRound().getCurrentPlayer().checkPenCount()) {
                        selectedCard.getPersonalWork().setHasPen(true);
                        game.getCurrentRound().getCurrentPlayer().removePen();
                    }

                    res.put(selectedCard, true);
                    updateData();
                    return res;
                }

                if (b == UV) {
                    HashMap<Card, Boolean> res = new HashMap<>();
                    res.put((Card) dialog.getSelectedItem(), false);
                    updateData();
                    return res;
                }

                return null;
            }
        });

        Optional result = dialog.showAndWait();
        if (result.isPresent()) {
            return (HashMap<Card, Boolean>) result.get();
        }
        return null;
    }


    private void createPlayers(ArrayList<Player> players) {
        playersList.getChildren().clear();

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

    private void showPlayerData() {
        Player currentPlayer = game.getCurrentRound().getCurrentPlayer();
        Inventory currentPlayerInventory = currentPlayer.getInventory();

        currentPlayerUsername.setText(currentPlayer.getUsername());
        creditCount.setText(String.valueOf(currentPlayerInventory.getCredits()));
        penCount.setText(String.valueOf(currentPlayerInventory.getPenCount()));
        mathCount.setText(String.valueOf(currentPlayerInventory.getSkillCount(Skill.MATH)));
        infoCount.setText(String.valueOf(currentPlayerInventory.getSkillCount(Skill.INFO)));
        energyCount.setText(String.valueOf(currentPlayerInventory.getSkillCount(Skill.ENERGY)));
        industryCount.setText(String.valueOf(currentPlayerInventory.getSkillCount(Skill.INDUSTRY)));
        ergoCount.setText(String.valueOf(currentPlayerInventory.getSkillCount(Skill.ERGO)));
        mechaCount.setText(String.valueOf(currentPlayerInventory.getSkillCount(Skill.MECHANICS)));
        managementCount.setText(String.valueOf(currentPlayerInventory.getSkillCount(Skill.MANAGEMENT)));
        languageCount.setText(String.valueOf(currentPlayerInventory.getSkillCount(Skill.LANGUAGE)));
    }

    private void showCardsOnBoard() {
        HashMap<Integer, Card> cards = new HashMap<>();
        for (int i = 0; i<game.getCurrentRound().getGameBoard().length; i++) {
            if (game.getCurrentRound().getGameBoard()[i] != null) {
                for (int j=0; j<game.getCurrentRound().getGameBoard()[i].size(); j++) {
                    cards.put(i+j*4, game.getCurrentRound().getGameBoard()[i].get(j));
                }
            } else {
                int k = i;
                for (int j=0; j<3; j++) {
                    AnchorPane anchorPane = getAnchorPaneFromPositionNumber(k);
                    anchorPane.getChildren().clear();
                    k += 4;
                }

            }

        }

        for (Map.Entry<Integer, Card> entry : cards.entrySet()) {
            displayCardOnBoard(entry.getValue(), getAnchorPaneFromPositionNumber(entry.getKey()));
        }
    }

    private void displayCardOnBoard(Card card, AnchorPane position) {
        AnchorPane uv = new AnchorPane();
        uv.setPrefWidth(164);
        uv.setPrefHeight(200);
        uv.setLayoutX(157.0);

        AnchorPane.setBottomAnchor(uv, 0.0);
        AnchorPane.setTopAnchor(uv, 0.0);
        AnchorPane.setRightAnchor(uv, 0.0);
        uv.setStyle("-fx-border-color: black;");

        AnchorPane pw = new AnchorPane();
        pw.setPrefWidth(155);
        pw.setPrefHeight(200);
        AnchorPane.setBottomAnchor(pw, 0.0);
        AnchorPane.setTopAnchor(pw, 0.0);
        AnchorPane.setLeftAnchor(pw, 0.0);

        // Adding elements in the Personal Work section (left section)
        Label bonus = new Label("Bonus");
        bonus.setFont(new Font("Verdana Bold", 12));
        AnchorPane.setRightAnchor(bonus,106.33333333333334);
        AnchorPane.setTopAnchor(bonus,14.0);

        ImageView bonusImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(getImageUrlFromBonus(card.getPersonalWork().getBonus())))));
        bonusImageView.setFitWidth(30);
        bonusImageView.setFitHeight(30);
        bonusImageView.setPickOnBounds(true);
        bonusImageView.setPreserveRatio(true);
        AnchorPane.setRightAnchor(bonusImageView, 104.33333333333334);
        AnchorPane.setTopAnchor(bonusImageView, 42.0);

        Label skill = new Label("Compétence");
        skill.setFont(new Font("Verdana Bold", 12));
        AnchorPane.setRightAnchor(skill,65.0);
        AnchorPane.setTopAnchor(skill,92.0);

        ImageView skillImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(getImageUrlFromSkill(card.getPersonalWork().getSkill())))));
        skillImageView.setFitWidth(30);
        skillImageView.setFitHeight(30);
        skillImageView.setPickOnBounds(true);
        skillImageView.setPreserveRatio(true);
        AnchorPane.setRightAnchor(skillImageView, 104.33333333333334);
        AnchorPane.setBottomAnchor(skillImageView, 42.66666666666666);

        // Adding elements in the UV section (right section)
        Label uvCode = new Label(card.getUv().getCode());
        uvCode.setFont(new Font("Verdana Bold", 18));
        AnchorPane.setRightAnchor(uvCode,50.33333333333334);
        AnchorPane.setTopAnchor(uvCode,14.0);

        Label categoryLabel = new Label("Catégorie :");
        categoryLabel.setFont(new Font(13));
        AnchorPane.setRightAnchor(categoryLabel,86.33333333333333);
        AnchorPane.setTopAnchor(categoryLabel,52.0);

        Label category = new Label(card.getUv().getUvCategory().toString());
        category.setFont(new Font(20));
        AnchorPane.setBottomAnchor(category, 119.66666666666666);
        AnchorPane.setRightAnchor(category,18.0);
        AnchorPane.setTopAnchor(category,42.0);

        Label requiredSkillLabel = new Label("Compétence requise");
        requiredSkillLabel.setFont(new Font("Verdana Bold", 13));
        AnchorPane.setRightAnchor(requiredSkillLabel,21.666666666666657);
        AnchorPane.setTopAnchor(requiredSkillLabel,100.0);

        ImageView requiredSkillImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(getImageUrlFromSkill(card.getUv().getSkill())))));
        requiredSkillImageView.setFitWidth(42);
        requiredSkillImageView.setFitHeight(42);
        requiredSkillImageView.setPickOnBounds(true);
        requiredSkillImageView.setPreserveRatio(true);
        AnchorPane.setRightAnchor(requiredSkillImageView, 64.33333333333334);
        AnchorPane.setTopAnchor(requiredSkillImageView, 133.0);

        pw.getChildren().addAll(bonus, bonusImageView, skill, skillImageView);
        uv.getChildren().addAll(uvCode, categoryLabel, category, requiredSkillLabel, requiredSkillImageView);

        position.getChildren().addAll(uv, pw);
    }

    private void showPlayerCards() {
        cardsList.getChildren().clear();

        for (UV uv : game.getCurrentRound().getCurrentPlayer().getInventory().getUvPossessed()) {
            displayCardUv(uv);
        }

        for (PersonalWork pw : game.getCurrentRound().getCurrentPlayer().getInventory().getPwPossessed()) {
            displayCardPersonalWork(pw);
        }
    }

    private void displayCardUv(UV uv) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(250);
        anchorPane.setStyle("-fx-border-color: black; -fx-background-color: white;");

        Label uvCode = new Label(uv.getCode());
        uvCode.setFont(new Font("Tahoma Bold", 24));
        uvCode.setLayoutX(107);
        uvCode.setLayoutY(26);
        AnchorPane.setBottomAnchor(uvCode, 326.0);
        AnchorPane.setRightAnchor(uvCode, 107.66666666666669);
        AnchorPane.setTopAnchor(uvCode, 26.0);

        Label category = new Label("Catégorie :");
        category.setFont(new Font(19));
        category.setLayoutX(14);
        category.setLayoutY(110);
        AnchorPane.setBottomAnchor(category, 243.0);
        AnchorPane.setLeftAnchor(category, 14.0);
        AnchorPane.setTopAnchor(category, 110.0);

        Label cat = new Label(uv.getUvCategory().toString());
        cat.setFont(new Font(28));
        cat.setLayoutX(192);
        cat.setLayoutY(105);
        AnchorPane.setBottomAnchor(cat, 242.0);
        AnchorPane.setRightAnchor(cat, 24.0);
        AnchorPane.setTopAnchor(cat, 109.0);

        Label requiredSkill = new Label("Compétence requise");
        requiredSkill.setFont(new Font("Verdana Bold", 13));
        requiredSkill.setLayoutX(53);
        requiredSkill.setLayoutY(210);
        AnchorPane.setBottomAnchor(requiredSkill, 166.0);
        AnchorPane.setRightAnchor(requiredSkill, 65.0);
        AnchorPane.setTopAnchor(requiredSkill, 211.0);

        Line line = new Line();
        line.setStartX(-100);
        line.setEndX(100);
        line.setLayoutX(139);
        line.setLayoutY(79);
        AnchorPane.setBottomAnchor(line, 315.8333333333333);
        AnchorPane.setRightAnchor(line, 39.166666666666686);
        AnchorPane.setTopAnchor(line, 78.5);
        AnchorPane.setLeftAnchor(line, 38.5);

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(getImageUrlFromSkill(uv.getSkill())))));
        imageView.setFitHeight(42);
        imageView.setFitWidth(42);
        imageView.setLayoutX(119);
        imageView.setLayoutY(286);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        AnchorPane.setBottomAnchor(imageView, 69.33333333333331);
        AnchorPane.setRightAnchor(imageView, 119.66666666666669);
        AnchorPane.setTopAnchor(imageView, 286.0);


        anchorPane.getChildren().addAll(uvCode, category, cat, requiredSkill, line, imageView);

        cardsList.getChildren().add(anchorPane);
    }

    private void displayCardPersonalWork(PersonalWork pw) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(200);
        anchorPane.setStyle("-fx-border-color: black; -fx-background-color: white;");

        Label bonusLabel = new Label("Bonus");
        bonusLabel.setFont(new Font("System Bold", 16));
        bonusLabel.setLayoutX(77);
        bonusLabel.setLayoutY(14);

        ImageView bonusImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(getImageUrlFromBonus(pw.getBonus())))));
        bonusImageView.setFitHeight(42);
        bonusImageView.setFitWidth(42);
        bonusImageView.setLayoutX(79);
        bonusImageView.setLayoutY(63);
        bonusImageView.setPickOnBounds(true);
        bonusImageView.setPreserveRatio(true);

        Label skillLabel = new Label("Compétence :");
        skillLabel.setFont(new Font("System bold", 16));
        skillLabel.setLayoutX(53);
        skillLabel.setLayoutY(174);

        ImageView skillImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(getImageUrlFromSkill(pw.getSkill())))));
        skillImageView.setFitHeight(42);
        skillImageView.setFitWidth(42);
        skillImageView.setLayoutX(32);
        skillImageView.setLayoutY(253);
        skillImageView.setPickOnBounds(true);
        skillImageView.setPreserveRatio(true);

        CheckBox checkBox = new CheckBox();
        checkBox.setLayoutX(121);
        checkBox.setLayoutY(265);
        checkBox.setText("Stylo");
        checkBox.setSelected(pw.isHasPen());
        Player currentPlayer = game.getCurrentRound().getCurrentPlayer();
        if (!currentPlayer.checkPenCount() && !checkBox.isSelected()) {
            checkBox.setDisable(true);
        }

        checkBox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    if (new_val) {
                        boolean hasEnoughPen = currentPlayer.checkPenCount();
                        if (hasEnoughPen) {
                            currentPlayer.removePen();
                            currentPlayer.getInventory().getPwPossessed().get(currentPlayer.getInventory().getPwPossessed().indexOf(pw)).setHasPen(true);
                        }
                    } else {
                        currentPlayer.addPen();
                        currentPlayer.getInventory().getPwPossessed().get(currentPlayer.getInventory().getPwPossessed().indexOf(pw)).setHasPen(false);
                    }

                    updateData();
                });

        anchorPane.getChildren().addAll(bonusLabel, bonusImageView, skillLabel, skillImageView, checkBox);

        cardsList.getChildren().add(anchorPane);
    }

    private void disableButtons() {
        if (game.getCurrentRound().getGameBoard()[0] == null) {
            firstColumnButton.setDisable(true);
        }

        if (game.getCurrentRound().getGameBoard()[1] == null) {
            secondColumnButton.setDisable(true);
        }

        if (game.getCurrentRound().getGameBoard()[2] == null) {
            thirdColumnButton.setDisable(true);
        }

        if (game.getCurrentRound().getGameBoard()[3] == null) {
            fourthColumnButton.setDisable(true);
        }
    }

    private void disableAllButtons() {
        firstColumnButton.setDisable(true);
        secondColumnButton.setDisable(true);
        thirdColumnButton.setDisable(true);
        fourthColumnButton.setDisable(true);
    }

    private void enableButtons() {
        if (game.getCurrentRound().getGameBoard()[0] != null) {
            firstColumnButton.setDisable(false);
        }

        if (game.getCurrentRound().getGameBoard()[1] != null) {
            firstColumnButton.setDisable(false);
        }

        if (game.getCurrentRound().getGameBoard()[2] != null) {
            firstColumnButton.setDisable(false);
        }

        if (game.getCurrentRound().getGameBoard()[3] != null) {
            firstColumnButton.setDisable(false);
        }
    }
    private String getImageUrlFromSkill(Skill skill) {
        String urlBase = "assets/";
        switch (skill) {
            case MATH:
                urlBase += "math.png";
                break;
            case INFO:
                urlBase += "info.png";
                break;
            case ENERGY:
                urlBase += "energy.png";
                break;
            case ERGO:
                urlBase += "ergo.png";
                break;
            case MECHANICS:
                urlBase += "mechanics.png";
                break;
            case INDUSTRY:
                urlBase += "industry.png";
                break;
            case MANAGEMENT:
                urlBase += "management.png";
                break;
            case LANGUAGE:
                urlBase += "language.png";
                break;
            default:
                break;
        }

        return urlBase;
    }

    private String getImageUrlFromBonus(Bonus bonus) {
        String urlBase = "assets/";
        switch (bonus) {
            case PEN:
                urlBase += "pen.png";
                break;
            case PROFESSOR:
                urlBase += "professor.png";
                break;
            case CREDIT:
                urlBase += "credit.png";
                break;
            case DOUBLE_CREDIT:
                urlBase += "double_credits.png";
                break;
            default:
                urlBase += "empty.png";
                break;
        }

        return urlBase;
    }

    private AnchorPane getAnchorPaneFromPositionNumber(int position) {
        AnchorPane anchorPane = null;
        switch (position) {
            case 0:
                anchorPane = one;
                break;
            case 1:
                anchorPane = two;
                break;
            case 2:
                anchorPane = three;
                break;
            case 3:
                anchorPane = four;
                break;
            case 4:
                anchorPane = five;
                break;
            case 5:
                anchorPane = six;
                break;
            case 6:
                anchorPane = seven;
                break;
            case 7:
                anchorPane = eight;
                break;
            case 8:
                anchorPane = nine;
                break;
            case 9:
                anchorPane = ten;
                break;
            case 10:
                anchorPane = eleven;
                break;
            case 11:
                anchorPane = twelve;
                break;
            default:
                break;
        }

        return anchorPane;
    }

    private void updateData() {
        showPlayerData();
        showPlayerCards();
        disableButtons();
    }
}
