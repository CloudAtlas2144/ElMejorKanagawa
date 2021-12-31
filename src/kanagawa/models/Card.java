package kanagawa.models;

import kanagawa.models.enums.CardState;

public class Card {
    private CardState state;
    private PersonalWork personalWork;
    private UV uv;

    // Default constructor
    public Card() {
    }

    // Initialization constructor
    public Card(PersonalWork personalWork, UV uv) {
        this.state = CardState.DECK;
        this.personalWork = personalWork;
        this.uv = uv;
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

    public void setState(CardState state) {
        this.state = state;
    }

    public void setPersonalWork(PersonalWork personalWork) {
        this.personalWork = personalWork;
    }

    public void setUv(UV uv) {
        this.uv = uv;
    }
}
