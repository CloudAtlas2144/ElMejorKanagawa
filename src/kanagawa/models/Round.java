package kanagawa.models;

import java.util.*;

public class Round {
    private int roundCount;

    private Player currentPlayer;

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
            // On initialise autant de colonne que de joueur
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
        for (int i = 0; i < cards.length; i++) {
            for (int j = index + 1; j < gameBoard.length; j++) {
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
        if (index >= 0) {
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

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public void setRemainingPlayers(ArrayList<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public ArrayList<Card>[] getGameBoard() {
        return gameBoard;
    }


    /**
     * Set the next player as the current player
     *
     * @return ArrayList
     */

    public void nextPlayer(){
        currentPlayer=players.get((players.indexOf(currentPlayer)+1)%players.size());
        currentPlayer.setPlaying(true);
    }
}
