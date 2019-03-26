package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CharacterDao {

    @Autowired
    DataSource dataSource;

    JdbcTemplate characterTemplate;

    public void setCharacterTemplate(JdbcTemplate characterTemplate) {
        this.characterTemplate = characterTemplate;
    }
//    public static final String CREATE_TABLE_CHARACTERS = "CREATE TABLE IF NOT EXISTS " + TABLE_CHARACTERS + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, "
//            + CHARACTERS_NAME + " VARCHAR(255) NOT NULL UNIQUE, " + CHARACTERS_RACE_ID + " INTEGER NOT NULL, " + CHARACTERS_ROLE_ID + " INTEGER NOT NULL, " +
//            CHARACTERS_SEX + " TEXT NOT NULL, " + CHARACTERS_LEVEL + " INTEGER NOT NULL, " + CHARACTERS_WINS + " INTEGER NOT NULL, " + CHARACTERS_LOSES +
//            " INTEGER NOT NULL, " + CHARACTERS_GOLD + " INTEGER NOT NULL,

    public void save(String race, String role, String gender, String name) {
        try(Connection conn = dataSource.getConnection()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Races WHERE race_name='" + race + "'");
            int raceId = 0;
            int roleId = 0;
            if(rs.next()){
                raceId = rs.getInt("_id");
            }
            rs = statement.executeQuery("SELECT * FROM Roles WHERE role='" + role + "'");
            if(rs.next()){
                roleId = rs.getInt("_id");
            }
            statement.executeUpdate("INSERT INTO Characters(character_name, race_id, role_id, sex, level, wins, loses, gold)" +
                    " VALUES('" + name + "', " + raceId + ", " + roleId + ", '" + gender + "', 1, 0, 0, 0)");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int selectCharacterIdByCharacterName(String name) {
        try(Connection conn = dataSource.getConnection()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Characters WHERE character_name='" + name + "'");
            if(rs.next()){
                return rs.getInt("_id");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

}
