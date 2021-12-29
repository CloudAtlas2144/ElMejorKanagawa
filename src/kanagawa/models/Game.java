package kanagawa.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private Round currentRound;
    private Player currentPlayer;

    private ArrayList<Player> players;
    private ArrayList<Card> cards;
    private ArrayList<Diploma> diplomas;

    private static Game gameInstance = null;

    /**
     *
     */
    private Game() {
        players = new ArrayList<>();
        cards = new ArrayList<>();
        diplomas = new ArrayList<>();
    }

    /**
     * Initializes the game instance if null and returns it
     * @return the game instance
     */
    public static Game getGameInstance() {
        if (gameInstance == null)
            new Game();

        return gameInstance;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Init all game static game objects
     */
    private void initObjects() {
        loadCards("");
        loadDiplomas("");
    }

    /**
     * Main game loop
     */
    public void gameLoop() {
        // TODO : Implement method
    }

    /**
     * Load cards data from json/xml files
     * @param fileName path to the file where the data is
     */
    private void loadCards(String fileName) {
        // TODO : Implement method
    }

    /**
     * Load diplomas data from json/xml files
     * @param fileName path to the file where the data is
     */
    private void loadDiplomas(String fileName) {
        // TODO : Implement method
    }

    /**
     * Set new cards on the board for the current round
     */
    public void distributeCards() {
        // TODO : Implement method
    }

    /**
     * Computes all players points at the end of the game
     * @return an HashMap with the points associated with the player
     */
    public HashMap<Player, Integer> computeTotalPoints() {
        // TODO : Implement method
        return null;
    }

    /**
     * Allows to end current round and to start a new one
     */
    public void nextRound() {
        // TODO : Implement method
    }


}
