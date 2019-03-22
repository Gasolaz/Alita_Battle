package DB;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static resources.Cons.URL_DB;

public class DBConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL_DB);
        return conn;
    }

}
