package controllers;

import dao.SessionsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/contact")
@Controller
public class Contact {

    @Autowired
    SessionsDao sessionsDao;

    @GetMapping
    public String getContact(@CookieValue(value= "sessionID", defaultValue = "0") String session){
        if(sessionsDao.checkExistingSession(session)){
            return "login";
        }
        return "contact";
    }
}
