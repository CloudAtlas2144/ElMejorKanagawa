package kanagawa.models;

import java.util.ArrayList;

public class Round {
    private int roundCount;

    private ArrayList<Card>[] gameBoard;

    Round() {
        gameBoard = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            gameBoard[i] = new ArrayList<Card>();
        }
    }

    /**
     * Deals 1 card to each column of the board
     * 
     * @param card a pointer on the array of cards to add
     */
    public void addCards(Card[] card) {
        int j = 0;
        for (int i = 0; i < card.length; i++) {
            while (gameBoard[j] == null && j < gameBoard.length)
                j++;
            gameBoard[j].add(card[j]);
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

    public void resetBoard() {
        // TODO : Implement method
    }

}
