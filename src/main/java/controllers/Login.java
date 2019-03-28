package controllers;

import dao.SessionsDao;
import dao.TablesDao;
import dao.UsersDao;
import models.RegistrationFormTempUser;
import models.Session;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import static resources.Cons.NO_ID;


@RequestMapping("/loggedIn")
@Controller
public class Login {

    @Autowired
    DataSource dataSource;

    @Autowired
    SessionsDao sessionsDao;

    @Autowired
    UsersDao usersDao;

    @GetMapping
    public String getLogin(Map<String, Object> model, @CookieValue(value= "sessionID", defaultValue = "0") String session) {
//        if(sessionsDao.checkExistingSession(session)){
//            return "login";
//        }
//        return "index";
        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "characterCreation";
            }
            return "loggedIn";
        }
        return "redirect:/";
    }

    @PostMapping
    public String postLogin(Map<String, Object> model, @ModelAttribute RegistrationFormTempUser rftu, HttpServletResponse response) {
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE username='" + rftu.getUsername() +
                    "' OR email='" + rftu.getUsername() + "'");
            if (rs.next()) {
                byte[] salt = fromHex(rs.getString("salt"));
                String generatedPass = UsersDao.get_SHA_256_SecurePassword(rftu.getPass(), salt);
                if (generatedPass.equals(rs.getString("hashed_pass"))) {
                    User user = usersDao.getUserByUsername(rftu.getUsername());
                    sessionsDao.save(user.get_id(), response);
                    return "redirect:/create";
                }
            }
            model.put("AccessDenied", "Error: Invalid credentials");
            return "index";
        } catch (SQLException e) {
            model.put("error", e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }


    public static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}

