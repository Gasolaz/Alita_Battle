package controllers;


import dao.SessionsDao;
import dao.TablesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RequestMapping("")
@Controller
public class HelloController {

    @Autowired
    TablesDao tablesDao;

    @Autowired
    SessionsDao sessionsDao;

    @GetMapping
    public String printHello(Map<String, Object> model, @CookieValue(value= "sessionID", defaultValue = "0") String session) {

        tablesDao.createTables();
        if(sessionsDao.checkExistingSession(session)){
            return "login";
        }
        return "index";
    }
}
