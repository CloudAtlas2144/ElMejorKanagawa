package kanagawa.models;

import kanagawa.models.enums.CardState;
import kanagawa.utilities.InvalidGameObjectException;

public class Card {
    private CardState state;
    private PersonalWork personalWork;
    private UV uv;



    private boolean isStarterCard;

    // Default constructor
    public Card() {

    }

    // Initialization constructor
    public Card(PersonalWork personalWork, UV uv) {
        this.state = CardState.DECK;
        this.personalWork = personalWork;
        this.uv = uv;
        this.isStarterCard = false;
    }

    /**
     * Checks if the Object has been parsed and initialized correctly
     * 
     * @throws InvalidGameObjectException
     */
    public void checkInitialization() throws InvalidGameObjectException {
        if (state == CardState.DECK && personalWork != null && uv != null) {
            personalWork.checkInitialization(this);
            uv.checkInitialization(this);
        } else {
            throw new InvalidGameObjectException(this);
        }
    }

    // Getters and setters
    public CardState getState() {
        return state;
    }

    public PersonalWork getPersonalWork() {
        return personalWork;
    }

    public UV getUv() {
        return uv;
    }

    public boolean isStarterCard() {
        return isStarterCard;
    }

    public void setState(CardState state) {
        this.state = state;
    }

    public void setPersonalWork(PersonalWork personalWork) {
        this.personalWork = personalWork;
    }

    public void setUv(UV uv) {
        this.uv = uv;
    }

    public void setStarterCard(boolean starterCard) {
        isStarterCard = starterCard;
    }

    @Override
    public String toString() {
        return "Card{" +
                "state=" + state +
                ", personalWork=" + personalWork +
                ", uv=" + uv +
                '}';
    }
}
