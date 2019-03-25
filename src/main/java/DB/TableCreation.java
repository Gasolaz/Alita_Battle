package DB;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import static resources.Cons.*;

public class TableCreation {

    @Autowired
    DataSource jt;

    public void createTables() {

//        File file = new File(URL_DB_FILE);
//        file.createNewFile();
        try (Connection conn = jt.getConnection()) {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
