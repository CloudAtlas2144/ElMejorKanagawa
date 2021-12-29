package kanagawa.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String username;
    private boolean isFirstPlayer;
    private Game game;

    private Inventory inventory;
    private List<Card> cards;

    // Constructor
    public Player(String username) {
        this.username = username;
        this.isFirstPlayer = false;
        this.inventory = new Inventory();
        this.cards = new ArrayList<>();

        game = Game.getGameInstance();
    }

    // Getters et setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isFirstPlayer() {
        return isFirstPlayer;
    }

    public void setFirstPlayer(boolean firstPlayer) {
        isFirstPlayer = firstPlayer;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * Add a card in the list of all cards owned by the player
     * @param card
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void takeCardDeck() {

    }

    public void addToInventory() {}

    public void addToDrawing() {}


}
