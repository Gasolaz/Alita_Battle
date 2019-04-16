package models.bl;

public class CharacterFormModelBL {
    public String race;
    public String role;
    public String name;
    public String gender;

    public CharacterFormModelBL() {
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }
}
