package kanagawa.models;

import kanagawa.models.enums.Skill;
import kanagawa.models.enums.UVCategory;
import kanagawa.utilities.InvalidGameObjectException;

public class UV {

    private String code;

    private UVCategory uvCategory;

    private Skill skill;

    public UV() {

    }

    public UV(String code, UVCategory uvCategory, Skill skill) {
        this.code = code;
        this.uvCategory = uvCategory;
        this.skill = skill;
    }

    /**
     * Checks if the Object has been parsed and initialized correctly
     * 
     * @param parent Collection in which the object is stored
     * @throws InvalidGameObjectException
     */
    public void checkInitialization(Card parent) throws InvalidGameObjectException {
        if (code == null || uvCategory == null || skill == null) {
            throw new InvalidGameObjectException(parent);
        }
    }

    public String getCode() {
        return code;
    }

    public UVCategory getUvCategory() {
        return uvCategory;
    }

    public Skill getSkill() {
        return skill;
    }
}
