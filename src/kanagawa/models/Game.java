package kanagawa.models;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import kanagawa.utilities.InvalidGameObjectException;

public class Game {
    private Round currentRound;
    private Player currentPlayer;
    private int roundCount; // To count how many rounds there were in the whole game
    private int indexFirstPlayer;

    private ArrayList<Player> players;
    private ArrayList<Card> cardDeck;
    private ArrayList<DiplomaGroup> diplomaGroups;

    public ArrayList<Card> getCardDeck() {
        return cardDeck;
    }

    private static Game gameInstance = null;

    /**
     *
     */
    private Game() {
        players = new ArrayList<Player>();
        cardDeck = new ArrayList<Card>();
        diplomaGroups = new ArrayList<DiplomaGroup>();
        currentRound = new Round(players);

        roundCount = 0;
        loadCards();
        loadDiplomas();

    }

    /**
     * Initializes the game instance if null and returns it
     * 
     * @return the game instance
     */
    public static Game getGameInstance() {
        if (gameInstance == null) {
            gameInstance = new Game();
        }

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
        loadCards();
        loadDiplomas();
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

        do {

            do {
                distributeCards();
                currentRound.playRound(indexFirstPlayer);
            } while (currentRound.getRoundCount() < 3 || currentRound.getRemainingColumns() != 0);
            nextRound();

        } while (true); // TODO : condition de victoire
    }

    /**
     * Load cards data from the json files and checks if they have been parsed
     * correctly
     */
    private void loadCards() {
        File file = new File("./cards.json");
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type cardListType = new TypeToken<ArrayList<Card>>() {
        }.getType();
        JsonReader jsonReader;

        try {
            jsonReader = new JsonReader(new FileReader(file));
            cardDeck = gson.fromJson(jsonReader, cardListType);
            for (Card card : cardDeck) {
                card.checkInitialization();
            }
            jsonReader.close();

        } catch (InvalidGameObjectException e) {
            e.printStackTrace();
            System.err.println("Index in cardDeck : " + cardDeck.indexOf(e.getObject()));
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Game.loadCards() : Failed to load cards.");
            System.exit(-1);
        }
    }

    /**
     * Load diplomas data from the json files and checks if they have been parsed
     * correctly
     */
    private void loadDiplomas() {
        File file = new File("./diplomas.json");
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type diplomaGroupListType = new TypeToken<ArrayList<DiplomaGroup>>() {
        }.getType();
        JsonReader jsonReader;

        try {
            jsonReader = new JsonReader(new FileReader(file));
            diplomaGroups = gson.fromJson(jsonReader, diplomaGroupListType);
            for (DiplomaGroup diplomaGroup : diplomaGroups) {
                diplomaGroup.checkInitialization();
            }
            jsonReader.close();

        } catch (InvalidGameObjectException e) {
            e.printStackTrace();
            Object object = e.getObject();
            Object parent = e.getParent();
            if (parent == null) {
                System.err.println("Index in diplomaGroups : " + diplomaGroups.indexOf(object));
            } else {
                int index = diplomaGroups.indexOf(parent);
                System.err.println("Index in diplomaGroups : " + index);
                System.err.println(
                        "Index of diploma in diplomas : " + diplomaGroups.get(index).getDiplomas().indexOf(object));
            }
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Game.loadCards() : Failed to load cards.");
            System.exit(-1);
        }
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
        currentRound = new Round(players);
        roundCount++;
        indexFirstPlayer = firstPlayer();
    }

    /**
     * Add all the players in the list
     * 
     * @param player1
     * @param player2
     * @param player3
     * @param player4
     */
    public void addPlayers(Player player1, Player player2, Player player3, Player player4) {
        players.add(player1);
        players.add(player2);
        if (player3 != null)
            players.add(player3);

        if (player4 != null)
            players.add(player4);
    }

    public void chooseRandomFirstPlayer() {
        Random rand = new Random();
        int i = rand.nextInt(players.size());
        indexFirstPlayer = i;
        Player player = players.get(i);
        player.setPlaying(true);
        player.setFirstPlayer(true);
        player.getInventory().setHasProfessor(true);

        currentPlayer = player;
        currentRound.setCurrentPlayer(players.get(i));

    }

    public int firstPlayer() {
        int i = 0;
        for (Player p : players) {
            if (p.isFirstPlayer()) {
                return i;
            } else {
                i++;
            }
        }
        return i;
    }

    /**
     * Getter for the players list
     * 
     * @return ArrayList
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

}
