package dao;

import models.CustomCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArenaDao {

    @Autowired
    DataSource dataSource;

    JdbcTemplate areneTemplate;

    public void setAreneTemplate(JdbcTemplate areneTemplate) {
        this.areneTemplate = areneTemplate;
    }

    public void insertPlayerToArena (int characterId, int enemyId){
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO Arena (character_id, enemy_id) VALUES (" + characterId + ", " + enemyId + ")");
            st.executeUpdate("INSERT INTO Arena (character_id, enemy_id) VALUES (" + enemyId + ", " + characterId + ")");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<CustomCharacter> selectFightsByCharacterId(int characterId){
        List<CustomCharacter> customCharacters = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            Statement statement2 = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Arena WHERE character_id=" + characterId);
            while(rs.next()){
                int enemyId = rs.getInt("enemy_id");
                ResultSet rs2 = statement2.executeQuery("SELECT cha.character_name, ra.race_name, ro.role FROM Characters AS cha INNER JOIN Races AS ra ON " +
                        "cha.race_id=ra._id INNER JOIN Roles AS ro ON cha.role_id=ro._id WHERE cha._id=" + enemyId);
                while(rs2.next()){
                    CustomCharacter customCharacter = new CustomCharacter();
                    customCharacter.setName(rs2.getString(1));
                    customCharacter.setRace(rs2.getString(2));
                    customCharacter.setRole(rs2.getString(3));
                    customCharacters.add(customCharacter);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return customCharacters;
    }
}
