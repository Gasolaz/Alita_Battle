package controllers;

import dao.*;
import interfaces.ICharacterDao;
import interfaces.IMsgDao;
import interfaces.ISessionsDao;
import interfaces.IUsersDao;
import models.dal.MessageDAL;
import models.bl.RegistrationFormTempUserBL;
import models.dal.UserDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static resources.Cons.NO_ID;


@RequestMapping("/AlitaBattle")
@Controller
public class Login {

    @Autowired
    DataSource dataSource;

    @Autowired
    ISessionsDao sessionsDao;

    @Autowired
    IUsersDao usersDao;

    @Autowired
    IMsgDao msgDao;

    @Autowired
    ICharacterDao characterDao;

    @GetMapping
    public String getLogin(Map<String, Object> model, @CookieValue(value= "sessionID", defaultValue = "0") String session) {
        int userId = sessionsDao.getUserIdFromSession(session);
        String userName = sessionsDao.getUserNameFromSession(userId); // (L) get 'userName' from ID

        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "characterCreation";
            }
            List<MessageDAL> messages = msgDao.getMessages();
            String characterName = characterDao.getCharacterNameById(usersDao.getCharacterIdFromUserId(userId));
            model.put("messages", messages);
            model.put("characterName", characterName); // (L) add to model 'characterName'

            return "loggedIn";
        }
        return "redirect:/";
    }

    @PostMapping
    public String postLogin(Map<String, Object> model, @ModelAttribute RegistrationFormTempUserBL rftu, HttpServletResponse response) {
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE username='" + rftu.getUsername() +
                    "' OR email='" + rftu.getUsername() + "'");
            if (rs.next()) {
                byte[] salt = fromHex(rs.getString("salt"));
                String generatedPass = UsersDao.get_SHA_256_SecurePassword(rftu.getPass(), salt);
                if (generatedPass.equals(rs.getString("hashed_pass"))) {
                    UserDAL user = usersDao.getUserByUsername(rftu.getUsername());
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

