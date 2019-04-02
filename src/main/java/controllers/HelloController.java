package controllers;


import dao.SessionsDao;
import dao.TablesDao;
import dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

import static resources.Cons.NO_ID;


@RequestMapping("")
@Controller
public class HelloController {

    @Autowired
    TablesDao tablesDao;

    @Autowired
    SessionsDao sessionsDao;

    @Autowired
    UsersDao usersDao;

    @GetMapping
    public String printHello(Map<String, Object> model, @CookieValue(value= "sessionID", defaultValue = "0") String session) {

        tablesDao.createTables();
        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "characterCreation";
            }
            return "redirect:/AlitaBattle";
        }

        return "index";

    }

    @GetMapping("/community")
    public String getCommunity(@CookieValue(value= "sessionID", defaultValue = "0") String session){
        return "community";
    }

    @GetMapping("/contact")
    public String getContact(@CookieValue(value= "sessionID", defaultValue = "0") String session){
        return "contact";
    }

    @GetMapping("/about")
    public String getAbout(@CookieValue(value= "sessionID", defaultValue = "0") String session){
        return "about";
    }
}
