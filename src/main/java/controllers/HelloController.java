package controllers;


import models.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static DB.DBConnection.getConnection;
import static DB.TableCreation.createTables;

@RequestMapping("")
@Controller
public class HelloController {

    @GetMapping
    public String printHello(Map<String, Object> model, @CookieValue(value= "sessionID", defaultValue = "0") String session) {

        try {
            createTables();
        } catch(IOException e){
            e.printStackTrace();
        }

        try(Connection conn = getConnection()){

            Session existingSession = new Session(session, conn);
            System.out.println(existingSession.getUserId());
            System.out.println(existingSession.isExistingSession());

            if(existingSession.isExistingSession()){
                model.put("greeting", "username reikia ieskot");
                return "login";
            }

        } catch (SQLException | ClassNotFoundException e){
            model.put("error", e.getMessage());
            e.printStackTrace();
            return "error";
        }
        return "index";
    }
}
