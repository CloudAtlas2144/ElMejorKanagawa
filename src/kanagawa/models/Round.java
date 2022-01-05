package kanagawa.models;

import java.util.ArrayList;

public class Round {
    private int roundCount;

    private Player currentPlayer;

    private ArrayList<Card>[] gameBoard;

    private ArrayList<Player> remainingPlayers = new ArrayList<>();

    Round(ArrayList<Player> players) {
        remainingPlayers = players;

        gameBoard = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            gameBoard[i] = new ArrayList<Card>();
        }
        roundCount = 0;
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
            currentPlayer.addToDrawing(card);
        } else {
            currentPlayer.addToInventory(card);
        }
    }

    public void removeFromRemainingPlayers(Player player) {
        remainingPlayers.remove(player);
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

    public ArrayList<Card>[] getGameBoard() {
        return gameBoard;
    }

}
