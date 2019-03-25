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
//        System.out.println();
//
//
//        try(Connection conn = jt.getConnection()){
//
//            TableCreation tc = new TableCreation();
//            tc.createTables();
//            Session existingSession = new Session(session, conn);
//
//            if(existingSession.isExistingSession()){
//                model.put("greeting", "username reikia ieskot");
//                return "login";
//            }
//
//        } catch (SQLException e){
//            model.put("error", e.getMessage());
//            e.printStackTrace();
//            return "error";
//        }
        return "index";
    }
}
