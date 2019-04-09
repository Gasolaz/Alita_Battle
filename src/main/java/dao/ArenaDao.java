package dao;

import models.BattlegroundCharacterModel;
import models.CustomCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
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

    public String checkIfResultIsEmpty(int characterId, int enemyId) {
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Arena WHERE character_id=" + characterId + " AND enemy_id="
                    + enemyId);
            if (rs.next() && rs.getString("result") != null) {
                return rs.getString("result");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteFightAndArenaForYou(int characterId, int enemyId) {
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM Arena WHERE character_id=" + characterId + " AND enemy_id=" + enemyId);
            st.executeUpdate("DELETE FROM Fight WHERE char_id=" + characterId + " AND enemy_id=" + enemyId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void resolveFight(int characterId, int enemyId) {
        int i = 0;
        int newHp1 = 1;
        int newHp2 = 1;
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Fight WHERE char_id=" + characterId + " AND enemy_id=" + enemyId);
            if (rs.next()) {
                String attacked1 = rs.getString("attack");
                String defended1 = rs.getString("defend");

                rs = st.executeQuery("SELECT * FROM Fight WHERE char_id=" + enemyId + " AND enemy_id=" + characterId);
                if (rs.next()) {
                    String attacked2 = rs.getString("attack");
                    String defended2 = rs.getString("defend");
                    if (!attacked1.equals(defended2)) {
                        rs = st.executeQuery("SELECT * FROM Arena WHERE character_id=" + enemyId + " AND enemy_id=" + characterId);
                        if (rs.next()) {
                            newHp1 = rs.getInt("hp") - 20;
                            st.executeUpdate("UPDATE Arena SET hp=" + newHp1 + " WHERE character_id=" + enemyId + " AND enemy_id=" + characterId);
                        }
                    }
                    if (!attacked2.equals(defended1)) {
                        rs = st.executeQuery("SELECT * FROM Arena WHERE character_id=" + characterId + " AND enemy_id=" + enemyId);
                        if (rs.next()) {
                            newHp2 = rs.getInt("hp") - 20;
                            st.executeUpdate("UPDATE Arena SET hp=" + newHp2 + " WHERE character_id=" + characterId + " AND enemy_id=" + enemyId);
                        }
                    }
                }
            }

            if (newHp1 <= 0 && newHp2 <= 0) {
                // draw logic
                st.executeUpdate("UPDATE Arena SET result='draw' WHERE character_id=" + characterId + " AND enemy_id=" + enemyId);
                st.executeUpdate("UPDATE Arena SET result='draw' WHERE character_id=" + enemyId + " AND enemy_id=" + characterId);
                st.executeUpdate("DELETE FROM Fight WHERE char_id=" + enemyId + " AND enemy_id=" + characterId);
                return;
            } else if (newHp1 <= 0) {
                // player2 wins
                st.executeUpdate("UPDATE Arena SET result='win' WHERE character_id=" + characterId + " AND enemy_id=" + enemyId);
                st.executeUpdate("UPDATE Arena SET result='lose' WHERE character_id=" + enemyId + " AND enemy_id=" + characterId);
                st.executeUpdate("DELETE FROM Fight WHERE char_id=" + enemyId + " AND enemy_id=" + characterId);
                return;
            } else if (newHp2 <= 0) {
                // player1 wins
                st.executeUpdate("UPDATE Arena SET result='lose' WHERE character_id=" + characterId + " AND enemy_id=" + enemyId);
                st.executeUpdate("UPDATE Arena SET result='win' WHERE character_id=" + enemyId + " AND enemy_id=" + characterId);
                st.executeUpdate("DELETE FROM Fight WHERE char_id=" + enemyId + " AND enemy_id=" + characterId);
                return;
            }
            st.executeUpdate("DELETE FROM Fight WHERE char_id=" + characterId + " AND enemy_id=" + enemyId);
            st.executeUpdate("DELETE FROM Fight WHERE char_id=" + enemyId + " AND enemy_id=" + characterId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfBothCharactersMadeADecision(int characterId, int enemyId) {
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Fight WHERE char_id=" + characterId + " AND enemy_id=" + enemyId);
            if (rs.next()) {
                rs = st.executeQuery("SELECT * FROM Fight WHERE char_id=" + enemyId + " AND enemy_id=" + characterId);
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkIfYouMadeADecision(int characterId, int enemyId) {
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Fight WHERE char_id=" + characterId + " AND enemy_id=" + enemyId);
            rs.next();
            if (rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public void insertMatchResults(int character_id, int enemy_id, String attack, String defend) {
        try (Connection conn = dataSource.getConnection()) {
            int characterHpId = 0;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Arena WHERE character_id=" + character_id + " AND enemy_id=" + enemy_id);
            if (rs.next()) {
                characterHpId = rs.getInt("_id");
            }
            st.executeUpdate("INSERT INTO Fight(char_id, enemy_id, attack, defend, hp_id) VALUES(" + character_id + ", " + enemy_id +
                    ", '" + attack + "', '" + defend + "', " + characterHpId + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPlayerToArena(int characterId, int enemyId) {
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Characters WHERE _id=" + characterId);
            BattlegroundCharacterModel battlegroundCharacterModel = new BattlegroundCharacterModel();
            int characterHp = 0;
            int enemyHp = 0;
            if (rs.next()) {
                battlegroundCharacterModel.setLevel(rs.getInt("level"));
//                battlegroundCharacterModel.setHp(rs.getInt("level"));
                characterHp = battlegroundCharacterModel.getHp();
            }
            rs = st.executeQuery("SELECT * FROM Characters WHERE _id=" + enemyId);
            if (rs.next()) {
                battlegroundCharacterModel.setLevel(rs.getInt("level"));
//                battlegroundCharacterModel.setHp(rs.getInt("level"));
                enemyHp = battlegroundCharacterModel.getHp();
            }
            st.executeUpdate("INSERT INTO Arena (character_id, enemy_id, hp) VALUES (" + characterId + ", " + enemyId + ", " + characterHp + ")");
            st.executeUpdate("INSERT INTO Arena (character_id, enemy_id, hp) VALUES (" + enemyId + ", " + characterId + ", " + enemyHp + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CustomCharacter> selectFightsByCharacterId(int characterId) {
        List<CustomCharacter> customCharacters = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            Statement statement2 = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Arena WHERE character_id=" + characterId);
            while (rs.next()) {
                int enemyId = rs.getInt("enemy_id");
                ResultSet rs2 = statement2.executeQuery("SELECT cha.character_name, ra.race_name, ro.role FROM Characters AS cha INNER JOIN Races AS ra ON " +
                        "cha.race_id=ra._id INNER JOIN Roles AS ro ON cha.role_id=ro._id WHERE cha._id=" + enemyId);
                while (rs2.next()) {
                    CustomCharacter customCharacter = new CustomCharacter();
                    customCharacter.setName(rs2.getString(1));
                    customCharacter.setRace(rs2.getString(2));
                    customCharacter.setRole(rs2.getString(3));
                    customCharacters.add(customCharacter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customCharacters;
    }
}
