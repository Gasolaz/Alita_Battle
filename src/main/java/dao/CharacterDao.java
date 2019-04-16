package dao;

import interfaces.IChallegesDao;
import interfaces.ICharacterDao;
import models.dal.BattlegroundCharacterModelDAL;
import models.dal.CharacterDAL;
import models.bl.CustomCharacterBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static resources.ConsTables.*;
import static resources.ConsTables.CHARACTERS_NAME;

public class CharacterDao implements ICharacterDao {

    @Autowired
    DataSource dataSource;

    JdbcTemplate characterTemplate;

    public void setCharacterTemplate(JdbcTemplate characterTemplate) {
        this.characterTemplate = characterTemplate;
    }

    public boolean isUsernameAlreadyTaken(String name) {
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Characters WHERE character_name='" + name + "'");
            rs.next();
            if (rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int[] displayResultInLoggedIn(int character_id) {
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Characters WHERE _id=" + character_id);
            if (rs.next()) {
                int[] array = {rs.getInt("wins"), rs.getInt("loses")};
                return array;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(String race, String role, String gender, String name) {
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            String image_link = null;
            switch (race) {
                case "dwarf":
                    image_link = "https://wow.zamimg.com/uploads/screenshots/normal/572806-simple.jpg";
                    break;
                case "human":
                    image_link = "https://wow.zamimg.com/uploads/screenshots/normal/562425-hunter-human-male.jpg";
                    break;
                case "orc":
                    image_link = "https://wow.zamimg.com/uploads/screenshots/normal/511437-sellsword.jpg";
                    break;
                case "elf":
                    image_link = "https://wow.zamimg.com/uploads/screenshots/normal/759517-blood-elf-ranger.jpg";
                    break;
                case "undead":
                    image_link = "https://wow.zamimg.com/uploads/screenshots/normal/566501-%D1%81%D0%B5%D1%82-1.jpg";
                    break;
            }
            ResultSet rs = statement.executeQuery("SELECT * FROM Races WHERE race_name='" + race + "'");
            int raceId = 0;
            int roleId = 0;
            if (rs.next()) {
                raceId = rs.getInt("_id");
            }
            rs = statement.executeQuery("SELECT * FROM Roles WHERE role='" + role + "'");
            if (rs.next()) {
                roleId = rs.getInt("_id");
            }
            statement.executeUpdate("INSERT INTO Characters(character_name, race_id, role_id, sex, exp, wins, loses, gold, right_h_id, left_h_id, torso_id, legs_id, image_link)" +
                    " VALUES('" + name + "', " + raceId + ", " + roleId + ", '" + gender + "', 0, 0, 0, 20, 0, 0, 0, 0,'" + image_link + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getImageLink(int characterId) {
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Characters WHERE _id=" + characterId);
            if (rs.next()) {
                return rs.getString("image_link");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCharacterAccordingToResult(int characterId, String result) {
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT wins, loses FROM Characters WHERE _id=" + characterId);
            rs.next();
            int wins = rs.getInt(1);
            int loses = rs.getInt(2);
            if (result.equals("win")) {
                wins++;
                statement.executeUpdate("UPDATE Characters SET wins=" + wins + " WHERE _id=" + characterId);
            } else if (result.equals("lose")) {
                loses++;
                statement.executeUpdate("UPDATE Characters SET loses=" + loses + " WHERE _id=" + characterId);
            } else if (result.equals("draw")) {
                // draw does nothing?
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<CharacterDAL> getAllCharactersExceptYourself(int characterId) {
        List<CharacterDAL> characterDALS = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Characters WHERE _id!=" + characterId + " AND _id NOT IN " +
                    "(SELECT challenged_character_id FROM Challenges WHERE character_id=" + characterId + ") AND _id NOT IN (SELECT character_id FROM Challenges WHERE " +
                    "challenged_character_id=" + characterId + ")");
            while (rs.next()) {
                CharacterDAL characterDAL = new CharacterDAL();
                characterDAL.set_id(rs.getInt(1));
                characterDAL.setCharacter_name(rs.getString(2));
                characterDAL.setRace(Integer.toString(rs.getInt(3)));
                characterDAL.setRole(Integer.toString(rs.getInt(4)));
                characterDAL.setSex(rs.getString(5));
                characterDAL.setLevel(rs.getInt(6));
                characterDAL.setWins(rs.getInt(7));
                characterDAL.setLoses(rs.getInt(8));
                characterDAL.setGold(rs.getInt(9));
                characterDALS.add(characterDAL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characterDALS;
    }

    public List<CustomCharacterBL> formCustomCharacterModel(List<CharacterDAL> characterDALS) {
        List<CustomCharacterBL> customCharacters = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            for (CharacterDAL characterDAL : characterDALS) {
                String sql = "SELECT ra.race_name, ro.role FROM Characters AS ch INNER JOIN Races AS ra ON ch.race_id=ra._id " +
                        "INNER JOIN Roles AS ro ON ch.role_id=ro._id WHERE ch._id=" + characterDAL.get_id();
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql);

                while (rs.next()) {
                    CustomCharacterBL customCharacter = new CustomCharacterBL();
                    customCharacter.setName(characterDAL.getCharacter_name());
                    customCharacter.setRace(rs.getString(1));
                    customCharacter.setRole(rs.getString(2));
                    customCharacters.add(customCharacter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customCharacters;
    }

    public int getCharacterIdFromCharacterName(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT _id FROM Characters WHERE character_name='" + name + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return NO_ID;
    }


    public int selectCharacterIdByCharacterName(String name) {
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Characters WHERE character_name='" + name + "'");
            if (rs.next()) {
                return rs.getInt("_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public BattlegroundCharacterModelDAL formBattlegroundCharacterModelFromCharacterId(int characterId) {
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT cha.character_name, ra.race_name, ro.role FROM Characters AS cha INNER JOIN Races AS ra " +
                    "ON ra._id=cha.race_id INNER JOIN Roles AS ro ON ro._id=cha.role_id WHERE cha._id=" + characterId);
            if (rs.next()) {
                int level = countLevelByExp(characterId)[0];
                BattlegroundCharacterModelDAL battlegroundCharacterModelDAL = new BattlegroundCharacterModelDAL();
                battlegroundCharacterModelDAL.setName(rs.getString(1));
                battlegroundCharacterModelDAL.setLevel(level);
                battlegroundCharacterModelDAL.setRace(rs.getString(2));
                battlegroundCharacterModelDAL.setRole(rs.getString(3));
                battlegroundCharacterModelDAL.setHp(level);
                battlegroundCharacterModelDAL.setMana(level);
                battlegroundCharacterModelDAL.setArmor(0);
                battlegroundCharacterModelDAL.setStrength(level);
                battlegroundCharacterModelDAL.setAgility(level);
                battlegroundCharacterModelDAL.setIntelligence(level);
                return battlegroundCharacterModelDAL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int[] countLevelByExp(int characterId) {
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Characters WHERE _id=" + characterId);
            if (rs.next()){
                int[] array = new int[3];
                int exp = rs.getInt("exp");
                array[1] = exp; // current xp
                int level = 0;
                while(exp >= 0){
                    level++;
                    exp = exp - 50*level;
                }

                array[0] = level; // current level
                int expToLevelUp = 0;
                for (int i = 0; i < level + 1; i++) {
                    expToLevelUp += 50 * i;
                }
                array[2] = expToLevelUp; // exp to level up
                return array;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getGold (int characterId) {
        try(Connection conn = dataSource.getConnection()){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Characters WHERE _id=" + characterId);
            if (rs.next()){
                int gold = rs.getInt("gold");
                return gold;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public int getTempHpForBattlegroundCharacterModel(int characterId, int enemyId) {
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT hp FROM Arena WHERE character_id=" + characterId + " AND enemy_id=" + enemyId);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getCharacterNameById(int char_id) {

        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT " + CHARACTERS_NAME + " FROM " + TABLE_CHARACTERS + " WHERE _id=" + char_id);
            if (rs.next()) {
                return rs.getString(CHARACTERS_NAME);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCharacterGold(int char_id) {
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT " + CHARACTERS_GOLD + " FROM " + TABLE_CHARACTERS + " WHERE _id=" + char_id);
            if (rs.next()) {
                return rs.getInt(CHARACTERS_GOLD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void reduceCharacterGold(int char_id, int item_price, int char_gold) {
        int remaining_gold = char_gold - item_price;
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE " + TABLE_CHARACTERS + " SET " + CHARACTERS_GOLD +
                    "=" + remaining_gold + " WHERE _id=" + char_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
