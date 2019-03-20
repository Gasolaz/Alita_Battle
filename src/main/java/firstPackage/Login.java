package firstPackage;

import models.Session;
import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        try (Connection conn = getConnection()){
            Session session = new Session("random", conn);
            if(session.isExistingSession()){
                // implement logic
                return "login";
            }
            return "redirect:/";
        } catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    @PostMapping
    public String postLogin(Map<String, Object> model, @ModelAttribute User user){
        try (Connection conn = getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE username='" + user.getUsername() + "' AND hashed_pass = '" + user.getPass() + "'");
            if (rs.next()) {
                return "login";
            } else {
                return "index";
            }
        } catch (SQLException | ClassNotFoundException e){
            model.put("error", e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }
}
