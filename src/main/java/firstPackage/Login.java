package firstPackage;

import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;
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
    public String getLogin() {
        return "redirect:/";
    }

    @PostMapping
    public String postLogin(Map<String, Object> model, @ModelAttribute User user){
        try (Connection conn = getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE username='" + user.getName() +  "' AND hashed_pass = '" + user.getPass() + "'");
            if (rs.next()) {
                model.put("error", "Username already exists");
                return "register";
            }
        }
            catch (SQLException | ClassNotFoundException e){
                model.put("error", e.getMessage());
                e.printStackTrace();
                return "error";
            }
        String greeting = "Hello " + user.getName();
        model.put("greeting", greeting);
        return "login";
    }
}
