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

public class ChallengesDao {

    @Autowired
    DataSource dataSource;

    JdbcTemplate challengeTemplate;

    public void setChallengeTemplate(JdbcTemplate challengeTemplate) {
        this.challengeTemplate = challengeTemplate;
    }

    public void insertChallenge(int characterId, int challengedId) {
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO Challenges(character_id, challenged_character_id) VALUES(" + characterId + ", " + challengedId + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CustomCharacter> getAllChallengesForYou(int userId) {
        List<CustomCharacter> customCharacters = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String getCharacterId = "SELECT character_id FROM Users WHERE _id=" + userId;
//            String sql = "SELECT cha.character_name, ra.race_name, ro.role FROM Users AS u INNER JOIN Characters AS cha ON cha._id=u.character_id INNER JOIN Roles AS ro ON " +
//                    "ro._id=cha.role_id INNER JOIN Races AS ra ON ra._id=cha.race_id INNER JOIN Challenges AS chal ON chal.challenged_character_id=cha._id WHERE u._id=" + userId;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(getCharacterId);
            if (rs.next()) {
                int id = rs.getInt("character_id");
                String sql = "SELECT cha.character_name, ra.race_name, ro.role FROM Characters AS cha INNER JOIN Roles AS ro ON ro._id=cha.role_id INNER JOIN Races AS ra ON " +
                        "ra._id=cha.race_id INNER JOIN Challenges AS chal ON chal.character_id=cha._id WHERE chal.challenged_character_id=" + id;
                rs = statement.executeQuery(sql);
                while (rs.next()) {
                    CustomCharacter customCharacter = new CustomCharacter();
                    customCharacter.setName(rs.getString(1));
                    customCharacter.setRace(rs.getString(2));
                    customCharacter.setRole(rs.getString(3));
                    customCharacters.add(customCharacter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customCharacters;
    }
}