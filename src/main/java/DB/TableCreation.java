package DB;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static DB.DBConnection.getConnection;
import static resources.Cons.*;

public class TableCreation {
    public static void createTables() throws IOException {

        File file = new File(URL_DB_FILE);
        file.createNewFile();
        try (Connection conn = getConnection()) {

//            Statement statement = conn.createStatement();
            PreparedStatement psUsers = conn.prepareStatement(CREATE_TABLE_USERS);
            PreparedStatement psChars = conn.prepareStatement(CREATE_TABLE_CHARACTERS);
            PreparedStatement psSess = conn.prepareStatement(CREATE_TABLE_SESSIONS);
            PreparedStatement psRac = conn.prepareStatement(CREATE_TABLE_RACES);
            PreparedStatement psInv = conn.prepareStatement(CREATE_TABLE_INVENTORY);

            psUsers.executeUpdate();
            psChars.executeUpdate();
            psSess.executeUpdate();
            psRac.executeUpdate();
            psInv.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
