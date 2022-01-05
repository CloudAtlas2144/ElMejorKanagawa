package kanagawa.models;

import java.util.*;

import kanagawa.models.enums.Bonus;

public class Round {
    private int roundCount;

    private Player currentPlayer;

    private int indexFirstPlayer;

    private ArrayList<Card>[] gameBoard;

    private ArrayList<Player> players;

    Round(ArrayList<Player> p) {
        gameBoard = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            gameBoard[i] = new ArrayList<Card>();
        }
        roundCount = 0;
    }

    public void initBoardWithPlayersCount() {
        for (int i = players.size(); i < gameBoard.length; i++) {
            //On initialise autant de colonne que de joueur
            gameBoard[i] = null;

        }
    }

    /**
     * Deals 1 card to each column of the board
     * 
     * @param cards a pointer on the array of cards to add
     */
    public void addCards(Card[] cards) {
        int index = -1;
        for (int i=0; i<cards.length; i++) {
            for (int j=index+1; j<gameBoard.length; j++) {
                if (gameBoard[j] != null) {
                    gameBoard[j].add(cards[i]);
                    index = j;

                    break;
                }

            }
        }
    }

    /**
     * Manage the round
     *
     * @param firstPlayer a list of players
     */
    public void playRound(int firstPlayer) { // TODO: exclure les joueurs qui ont déjà joué

        // variable to stock the column selected by the player
        int takeCards = -99; // initialize at the value were the player pass
        int numberPlayerAtStart = players.size(); // if there's only on player at the start he must took a column
        int indexCurrentPlayer = firstPlayer;
        indexFirstPlayer = firstPlayer;
        currentPlayer = players.get(indexCurrentPlayer);
        boolean choiceCard = false;// true => the player keep the uv | false => the player keep the personalWork

        for (int i = 0; i < numberPlayerAtStart; i++) {

            // TODO: faire choisir le joueur

            // case : the player select a column
            if (takeCards != -99) {
                currentPlayer.takeCardColumn(removeColumn(takeCards));
            } else {
                // case : there is one column remaining or there is no place left to set cards
                // on the board
                if (numberPlayerAtStart == 1 || getRoundCount() == 3) {

                    while (takeCards == -99) {
                        // TODO: faire choisir le joueur
                        for (Card card : currentPlayer.getCards()) {
                            // TODO : faire choisir le joueur et changer choiceCard
                            choiceCard(choiceCard, card);
                        }
                    }
                    currentPlayer.takeCardColumn(removeColumn(takeCards));
                }
            }
            // Player took card, he use it
            if (takeCards != -99) {
                // TODO : usecards()

                players.remove(indexCurrentPlayer);
                currentPlayer = players.get(indexCurrentPlayer);
            } else {
                indexCurrentPlayer = indexCurrentPlayer + 1 % players.size();
            }
        }
    }

    /**
     * Removes the specified column of cards from the board and returns it
     * 
     * @return an {@code ArrayList<Card>}
     */
    public ArrayList<Card> removeColumn(int index) {
        ArrayList<Card> temp = null;
        if (index < getRemainingColumns() && index > 0) {
            temp = this.gameBoard[index];
            this.gameBoard[index] = null;
        } else {
            System.err.println("Round.removeColumn() : Invalid index value.");
            System.exit(-1);
        }
        return temp;
    }

    /**
     * Computes the number of columns of cards that remain on the board
     * 
     * @return an {@code int}
     */
    public int getRemainingColumns() {
        int j = 0;
        for (int i = 0; i < 4; i++) {
            if (gameBoard[i] != null)
                j++;
        }
        return j;
    }

    /**
     * Manage the use of a card
     * 
     * true = player use the uv part
     * false = player use the personalWork part
     */
    public void choiceCard(Boolean choicecard, Card card) {
        if (choicecard) {
            currentPlayer.addToUv(card);
        } else {
            currentPlayer.addToPersonalWork(card);
            if (card.getPersonalWork().getBonus() == Bonus.PROFESSOR) {
                players.get(indexFirstPlayer).setFirstPlayer(false);
            }
        }
    }

    public void removeFromRemainingPlayers(Player player) {
        players.remove(player);
    }

    public void resetBoard() {
        // TODO : Implement method
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public void setRemainingPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Card>[] getGameBoard() {
        return gameBoard;
    }

}
