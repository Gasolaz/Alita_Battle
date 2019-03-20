package models;

public class User {

    private String username;
    private String pass;
    private String email;
    private Long character_id;

    public User() {
    }

    public User(String name, String pass, String email, Long character_id) {
        this.username = name;
        this.pass = pass;
        this.email = email;
        this.character_id = character_id;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
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
