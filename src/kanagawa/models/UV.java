package kanagawa.models;

import kanagawa.models.enums.Skill;
import kanagawa.models.enums.UVCategory;

public class UV {

    private String code;

    private UVCategory uvCategory;

    private Skill skill;

    public UV(String code, UVCategory uvCategory, Skill skill) {
        this.code = code;
        this.uvCategory = uvCategory;
        this.skill = skill;
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
