package firstPackage;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static DB.DBConnection.getConnection;
import static DB.TableCreation.createTables;

@RequestMapping("")
@Controller
public class HelloController {

    @GetMapping
    public String printHello(ModelMap model) {
        Connection conn = null;
        try {
            createTables();
            conn = getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users");
            while(rs.next()){
                System.out.println(rs.getString("username"));
            }
        } catch(IOException | ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e){
                    System.out.println("pz");
                }
            }
        }

        model.addAttribute("message", "Welcome to Alita Battle!");
        return "hello";

    }
}
