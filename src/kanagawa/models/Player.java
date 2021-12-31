package kanagawa.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String username;
    private boolean isFirstPlayer;
    private Game game;

    private Inventory inventory;

    /**
     * Cards that the player will have to add either to his inventory as UVs or
     * Personnal Work
     */
    private List<Card> cardsInHand;

    // Constructor
    public Player(String username) {
        this.username = username;
        this.isFirstPlayer = false;
        this.inventory = new Inventory();
        this.cardsInHand = new ArrayList<>();

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
        return cardsInHand;
    }

    /**
     * Add a card in the list of all cards owned by the player
     * 
     * @param card
     */
    public void addCard(Card card) {
        this.cardsInHand.add(card);
    }

    /**
     * Adds a column of cards from the board to the player's hand
     * 
     * @param cardColumn
     */
    public void takeCardColumn(Card[] cardColumn) {
        for (int i = 0; i < cardColumn.length; i++) {
            cardsInHand.add(cardColumn[i]);
        }

        cardsInHand.addAll(List.of(cardColumn));
    }

    public void addToInventory() {
    }

    public void addToDrawing() {
    }

}
