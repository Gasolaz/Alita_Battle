package DB;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:.." + File.separator + ".." + File.separator + ".." + File.separator +
                "src" + File.separator + "Database.db");
        return conn;
    }

}
