package kanagawa.models;

import java.util.ArrayList;
import java.util.List;
import kanagawa.models.enums.Bonus;

public class Player {
    private String username;
    private boolean isFirstPlayer;

    private boolean isPlaying;
    private Game game;

    private Inventory inventory;

    /**
     * Cards that the player will have to add either to his inventory as UVs or
     * Personnal Work
     */
    private ArrayList<Card> cardsInHand;

    // Constructor
    public Player(String username) {
        this.username = username;
        this.isFirstPlayer = false;
        this.isPlaying = false;
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

    public ArrayList<Card> getCards() {
        return cardsInHand;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
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
    public void takeCardColumn(ArrayList<Card> cardColumn) {
        cardsInHand.addAll(cardColumn);
    }

    public void addToPersonalWork(Card card) {
        inventory.addPersonalWork(card.getPersonalWork());

        if (card.getPersonalWork().getBonus() == Bonus.PEN) {
            inventory.addPen();
        }
        if (card.getPersonalWork().getBonus() == Bonus.CREDIT) {
            this.inventory.setCredits(inventory.getCredits() + 1);
        }
        if (card.getPersonalWork().getBonus() == Bonus.DOUBLE_CREDIT) {
            this.inventory.setCredits(inventory.getCredits() + 2);
        }
        if (card.getPersonalWork().getBonus() == Bonus.PROFESSOR) {
            this.setFirstPlayer(true);
        }
    }

    public void addToUv(Card card) {
        inventory.addUv(card.getUv());
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", isFirstPlayer=" + isFirstPlayer +
                ", game=" + game +
                ", inventory=" + inventory +
                ", cardsInHand=" + cardsInHand +
                '}';
    }
}
