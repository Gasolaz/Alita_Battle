package models;

public class Challenge {
    public int id;
    public int character_id;
    public int challenged_character_id;

    public Challenge() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(int character_id) {
        this.character_id = character_id;
    }

    public int getChallenged_character_id() {
        return challenged_character_id;
    }

    public void setChallenged_character_id(int challenged_character_id) {
        this.challenged_character_id = challenged_character_id;
    }
}
