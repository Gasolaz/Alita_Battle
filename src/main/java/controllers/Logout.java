package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

@RequestMapping("/logout")
@Controller
public class Logout {

    @Autowired
    DataSource dataSource;

    @GetMapping
    public String getLogout(Map<String, Object> model, @CookieValue(value="sessionID", defaultValue = "0") String session) {
        try(Connection conn = dataSource.getConnection()){
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM Sessions WHERE hashed_session='" + session + "'");
            return "redirect:/";
        } catch (SQLException e){
            model.put("error", e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }
}
