package controllers;

import models.Session;
import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import static DB.DBConnection.getConnection;

@RequestMapping("/login")
@Controller
public class Login {

    @GetMapping
    public String getLogin(Map<String, Object> model) {
        try (Connection conn = getConnection()) {
            Session session = new Session("random", conn);
            if (session.isExistingSession()) {
                // implement logic
                return "login";
            }
            return "redirect:/register";
        } catch (SQLException | ClassNotFoundException e) {
            model.put("error", e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping
    public String postLogin(Map<String, Object> model, @ModelAttribute User user) {
        try (Connection conn = getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE username='" + user.getUsername() +
                    "' OR email='" + user.getUsername() + "'");
            if (rs.next()) {
                byte[] salt = fromHex(rs.getString("salt"));
                String generatedPass = Registration.get_SHA_256_SecurePassword(user.getPass(), salt);
                if (generatedPass.equals(rs.getString("hashed_pass"))) {
                    String greeting = "Hello " + rs.getString("username");
                    model.put("greeting", greeting);
                    return "login";
                }
            }
            // nu cia backe tikrina ar egzistuoja toks useris, kaip verifikacija padaryt popupe tai bbz, sugalvosim kazka
            // random returnas vien del testo, negaliu daryt redirecto nes modelio nepaema, kasnors turi ideju kaip redirectint su modeliu?
//            redirectAttributes.addAttribute("AccessDenied", "Error: Invalid credentials");
//            return "redirect:/";
            return "index";
        } catch (SQLException | ClassNotFoundException e) {
            model.put("error", e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }

    byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}

