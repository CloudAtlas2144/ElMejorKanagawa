package kanagawa.models;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Game {
    private Round currentRound;
    private Player currentPlayer;

    private ArrayList<Player> players;
    private ArrayList<Card> cardDeck;
    private ArrayList<Diploma> diplomas;

    private static Game gameInstance = null;

    /**
     *
     */
    private Game() {
        players = new ArrayList<>();
        cardDeck = new ArrayList<>();
        diplomas = new ArrayList<>();
        loadCards("");
    }

    /**
     * Initializes the game instance if null and returns it
     * 
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
        /*
         * Create a Round instance
         * Deal the cards
         */

    }

    /**
     * Load cards data from json/xml files
     * 
     * @param fileName path to the file where the data is
     */
    private void loadCards(String fileName) {
        File file = new File("./cards.json");
        Gson gson = new Gson();
        Type cardListType = new TypeToken<ArrayList<Card>>() {
        }.getType();

        JsonReader jsonReader;

        try {
            jsonReader = new JsonReader(new FileReader(file));
            jsonReader.setLenient(true);

            cardDeck = gson.fromJson(jsonReader, cardListType);

            jsonReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Game.loadCards() : Failed to load cards.");
            System.exit(-1);
        }
    }

    /**
     * Load diplomas data from json/xml files
     * 
     * @param fileName path to the file where the data is
     */
    private void loadDiplomas(String fileName) {
        // TODO : Implement method
    }

    /**
     * Deals new cards on the board for the current round and passes them to the
     * Round class
     */
    public void distributeCards() {
        Card[] cardsToDeal = new Card[currentRound.getRemainingColumns()];

        for (int i = 0; i < cardsToDeal.length; i++) {
            cardsToDeal[i] = cardDeck.remove(0);
        }
        currentRound.addCards(cardsToDeal);
    }

    /**
     * Computes all players points at the end of the game
     * 
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
