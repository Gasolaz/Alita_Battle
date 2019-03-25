package controllers;

import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static DB.DBConnection.getConnection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Map;


@RequestMapping(value="/register", consumes = "application/json")
@Controller
public class Registration {

    @GetMapping
    public String getRegistration() {
        return "redirect:/";
    }

    @PostMapping
    public String postRegistration(Map<String, Object> model, @ModelAttribute User user) {
        try (Connection conn = getConnection()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT username FROM Users WHERE username='" + user.getUsername() + "'");
            if(rs.next()){
                model.put("error", "Username already exists");
                return "register";
            }
            byte[] salt = getSalt();
            String generatedPass = get_SHA_256_SecurePassword(user.getPass(), salt);
            statement.executeUpdate("INSERT INTO Users(username, hashed_pass, salt, email, isAdmin) VALUES('" + user.getUsername()
            + "', '" + generatedPass + "', '" + salt + "', '" + user.getEmail() + "', 0)");
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e){
            model.put("error", e.getMessage());
            e.printStackTrace();
            return "error";
        }
        String greeting = "Hello " + user.getUsername();
        model.put("greeting", greeting);
        return "login";
    }

    byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
