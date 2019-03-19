package models;

public class User {

    private String name;
    private String pass;
    private String email;
    private Long character_id;

    public User() {
    }

    public User(String name, String pass, String email, Long character_id) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.character_id = character_id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public Long getCharacter_id() {
        return character_id;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCharacter_id(Long character_id) {
        this.character_id = character_id;
    }
}
