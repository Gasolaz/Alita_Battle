package controllers;

import interfaces.ISessionsDao;
import interfaces.IUsersDao;
import dao.SessionsDao;
import models.bl.RegistrationFormTempUserBL;
import models.dal.UserDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Controller
public class Registration {

    @Autowired
    IUsersDao usersDao;

    @Autowired
    ISessionsDao sessionsDao;

    @GetMapping("/register")
    public String getRegistration(@CookieValue(value= "sessionID", defaultValue = "0") String session) {
        if(sessionsDao.checkExistingSession(session)){
            return "redirect:/";
        }
        return "registration";
    }

    @PostMapping("/register")
    public String postRegistration(Map<String, Object> model, @ModelAttribute RegistrationFormTempUserBL rftu, HttpServletResponse response) {

        if (rftu.getUsername().trim().equals("") || rftu.getEmail().trim().equals("") || rftu.getPass().trim().equals("")) {
            model.put("error", "All fields must be filled");
            return "registration";
        } else if (!rftu.getPass().trim().equals(rftu.getPassConfirmation().trim())) {
            model.put("error", "Passwords must match");
            return "registration";
        }

        List<UserDAL> users = usersDao.getUsersByUsernameOrEmail(rftu.getUsername(), rftu.getEmail());

        if (users.size() > 0) {
            model.put("error", "Username or email already exists");
            return "registration";
        }

        usersDao.save(rftu.getUsername(), rftu.getEmail(), rftu.getPass());
        UserDAL user = usersDao.getUserByUsername(rftu.getUsername());
        sessionsDao.save(user.get_id(), response);
        return "redirect:/create";
    }
}
