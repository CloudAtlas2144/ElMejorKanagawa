package kanagawa.models;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private List<Card> cards;

    // Constructors
    public CardDeck() {
        this.cards = new ArrayList<>();
    }
    public CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    // Getters and setters
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * Add card into the deck
     * @param card
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }
}
