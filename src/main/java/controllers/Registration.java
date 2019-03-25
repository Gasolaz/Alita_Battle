package controllers;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import static resources.Cons.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Map;
import java.util.Random;


@RequestMapping("/register")
@Controller
public class Registration {

    @Autowired
    DataSource jt;

    @GetMapping
    public String getRegistration() {
        return "registration";
    }

    @PostMapping
    public String postRegistration(Map<String, Object> model, @ModelAttribute User user, HttpServletResponse response) {
        try (Connection conn = jt.getConnection()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE LOWER(username)='" +
                    user.getUsername().toLowerCase() + "' OR LOWER(email)='" + user.getEmail().toLowerCase() + "'");
            if(rs.next()){
                model.put("error", "Username or email already exists");
                return "registration";
            } else if (user.getUsername().equals("") || user.getEmail().equals("") || user.getPass().equals("")){
                model.put("error", "All fields must be filled");
                return "registration";
            } else if (!user.getPass().equals(user.getPassConfirmation())){
                model.put("error", "Passwords must match");
                return "registration";
            }
            byte[] salt = getSalt();
            String generatedPass = get_SHA_256_SecurePassword(user.getPass(), salt);
            statement.executeUpdate("INSERT INTO Users(username, hashed_pass, salt, email, isAdmin) VALUES('" + user.getUsername()
            + "', '" + generatedPass + "', '" + fromByteArrayToString(salt) + "', '" + user.getEmail().toLowerCase() + "', 0)");

            Statement searchingForUserID = conn.createStatement();
            rs = searchingForUserID.executeQuery("SELECT " + ID + " FROM " + TABLE_USERS +
                    " WHERE LOWER(username)='" + user.getUsername().toLowerCase() + "'");

            String sessionID = new Random().nextLong() + "";
            Statement insertIntoSessions = conn.createStatement();
            insertIntoSessions.executeUpdate("INSERT INTO " + TABLE_SESSIONS + "(hashed_session, user_id) VALUES (" +
                    sessionID + ", " + rs.getLong("_id")+ ")");

            Cookie cookie = new Cookie("sessionID", sessionID);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(cookie);
        } catch (SQLException | NoSuchAlgorithmException e){
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

    public static String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            generatedPassword = fromByteArrayToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    static String fromByteArrayToString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}
