package DB;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static DB.DBConnection.getConnection;

public class TableCreation {
    public static void createTables() throws IOException {

        File file = new File(".." + File.separator + ".." + File.separator + ".." + File.separator + "src" + File.separator + "Database.db");
        file.createNewFile();
        try (Connection conn = getConnection()) {

            Statement statement = conn.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users ('_id' INTEGER NOT NULL, 'username' TEXT NOT NULL, 'hashed_pass' TEXT NOT NULL, " +
                    "'salt' TEXT NOT NULL, 'email' TEXT NOT NULL, 'character_id' INTEGER, 'isAdmin' BOOLEAN NOT NULL, PRIMARY KEY('_id'))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Characters ('_id' INTEGER NOT NULL, 'race_id' INTEGER NOT NULL, 'sex' TEXT NOT NULL, " +
                    "'level' INTEGER NOT NULL, 'wins' INTEGER NOT NULL, 'loses' INTEGER NOT NULL, 'gold' INTEGER NOT NULL, PRIMARY KEY('_id'))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Sessions ('_id' INTEGER NOT NULL,'hashed_session' TEXT NOT NULL, PRIMARY KEY('_id'))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Races ('_id' INTEGER NOT NULL,'race_name' TEXT NOT NULL,'racial_perk' TEXT NOT NULL, PRIMARY KEY('_id'))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Inventory ('_id' INTEGER NOT NULL,'item_name' TEXT NOT NULL,PRIMARY KEY('_id'))");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
