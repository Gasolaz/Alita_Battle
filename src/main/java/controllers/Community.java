package controllers;

import dao.SessionsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/community")
@Controller
public class Community {

    @Autowired
    SessionsDao sessionsDao;

    @GetMapping
    public String getCommunity(@CookieValue(value= "sessionID", defaultValue = "0") String session){
        return "community";
    }
}