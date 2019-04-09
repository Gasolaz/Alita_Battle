package models;

public class RegistrationFormTempUserBL {
    public String username;
    public String email;
    public String pass;
    public String passConfirmation;

    public RegistrationFormTempUserBL() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPassConfirmation() {
        return passConfirmation;
    }

    public void setPassConfirmation(String passConfirmation) {
        this.passConfirmation = passConfirmation;
    }
}
