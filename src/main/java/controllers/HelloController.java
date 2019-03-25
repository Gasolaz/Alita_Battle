package controllers;

import DB.TableCreation;
import dao.TablesDao;
import models.Session;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;


@RequestMapping("")
@Controller
public class HelloController {

    @Autowired
    TablesDao tablesDao;
    @Autowired
    DataSource jt;

    @GetMapping
    public String printHello(Map<String, Object> model, @CookieValue(value= "sessionID", defaultValue = "0") String session, @ModelAttribute User user) {

        tablesDao.createTables();

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

        try (Connection conn = jt.getConnection()) {
            Session session1 = new Session(session, conn);
            if (session1.isExistingSession()) {
                String greeting = "Hello " + user.getUsername();
                model.put("greeting", greeting);
                return "login";
            }
            return "index";
        } catch (SQLException e) {
            model.put("error", e.getMessage());
            e.printStackTrace();
            return "error";
        }

//        return "index";

    }

}
