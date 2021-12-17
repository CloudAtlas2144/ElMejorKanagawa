package kanagawa.models;

import kanagawa.models.enums.CardState;

public class Card {
    private CardState state;
    private Skill primarySkill, secondarySkill;
    private UV uv;

    // Default constructor
    public Card() {}
    // Initialization constructor
    public Card(CardState state, Skill primarySkill, Skill secondarySkill, UV uv) {
        this.state = state;
        this.primarySkill = primarySkill;
        this.secondarySkill = secondarySkill;
        this.uv = uv;
    }

    // Getters and setters
    public CardState getState() {
        return state;
    }

    public Skill getPrimarySkill() {
        return primarySkill;
    }

    public Skill getSecondarySkill() {
        return secondarySkill;
    }

    public UV getUv() {
        return uv;
    }

    public void setState(CardState state) {
        this.state = state;
    }

    public void setPrimarySkill(Skill primarySkill) {
        this.primarySkill = primarySkill;
    }

    public void setSecondarySkill(Skill secondarySkill) {
        this.secondarySkill = secondarySkill;
    }

    public void setUv(UV uv) {
        this.uv = uv;
    }
}
