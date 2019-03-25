package models;

public class User {

    private String username;
    private String pass;
    private String passConfirmation;
    private String email;
    private Long character_id;

    public User() {
    }

    public String getPassConfirmation() {
        return passConfirmation;
    }

    public void setPassConfirmation(String passConfirmation) {
        this.passConfirmation = passConfirmation;
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
