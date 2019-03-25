package controllers;


import DB.TableCreation;
import dao.TablesDao;
import models.Session;
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

    @GetMapping
    public String printHello(Map<String, Object> model, @CookieValue(value= "sessionID", defaultValue = "0") String session) {

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
        return "index";
    }
}
