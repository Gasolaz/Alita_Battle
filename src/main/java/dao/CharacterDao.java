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

import static resources.Cons.*;
import static resources.Cons.CHARACTERS_NAME;

public class CharacterDao implements ICharacterDao {

    @Autowired
    DataSource dataSource;

    JdbcTemplate characterTemplate;

    public void setCharacterTemplate(JdbcTemplate characterTemplate) {
        this.characterTemplate = characterTemplate;
    }

    public boolean isUsernameAlreadyTaken(String name){
        try (Connection conn = dataSource.getConnection()){
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Characters WHERE character_name='" + name + "'");
             rs.next();
             if(rs.getInt(1) > 0){
                 return true;
             }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void save(String race, String role, String gender, String name) {
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
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
            statement.executeUpdate("INSERT INTO Characters(character_name, race_id, role_id, sex, level, wins, loses, gold, item_set_id)" +
                    " VALUES('" + name + "', " + raceId + ", " + roleId + ", '" + gender + "', 1, 0, 0, 0, 0)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCharacterAccordingToResult (int characterId, String result){
        try (Connection conn = dataSource.getConnection()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT wins, loses FROM Characters WHERE _id=" + characterId);
            rs.next();
            int wins = rs.getInt(1);
            int loses = rs.getInt(2);
            if(result.equals("win")){
                wins++;
                statement.executeUpdate("UPDATE Characters SET wins=" + wins + " WHERE _id=" + characterId);
            } else if(result.equals("lose")){
                loses++;
                statement.executeUpdate("UPDATE Characters SET loses=" + loses + " WHERE _id=" + characterId);
            } else if (result.equals("draw")){
                // draw does nothing?
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

    }


    public List<CharacterDAL> getAllCharactersExceptYourself(int characterId) {
        List<CharacterDAL> characterDALS = new ArrayList<>();
        try(Connection conn = dataSource.getConnection()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Characters WHERE _id!=" + characterId + " AND _id NOT IN " +
                    "(SELECT challenged_character_id FROM Challenges WHERE character_id=" + characterId + ") AND _id NOT IN (SELECT character_id FROM Challenges WHERE " +
                    "challenged_character_id=" + characterId + ")");
            while (rs.next()){
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
        } catch (SQLException e){
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

                while(rs.next()){
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

    public int getCharacterIdFromCharacterName(String name){
        try (Connection conn = dataSource.getConnection()){
            String sql = "SELECT _id FROM Characters WHERE character_name='" + name + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("_id");
            }
        } catch (SQLException e){
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

    public BattlegroundCharacterModelDAL formBattlegroundCharacterModelFromCharacterId(int characterId){
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT cha.character_name, cha.level, ra.race_name, ro.role FROM Characters AS cha INNER JOIN Races AS ra " +
                    "ON ra._id=cha.race_id INNER JOIN Roles AS ro ON ro._id=cha.role_id WHERE cha._id=" + characterId);
            if(rs.next()){
                int level = rs.getInt(2);
                BattlegroundCharacterModelDAL battlegroundCharacterModelDAL = new BattlegroundCharacterModelDAL();
                battlegroundCharacterModelDAL.setName(rs.getString(1));
                battlegroundCharacterModelDAL.setLevel(level);
                battlegroundCharacterModelDAL.setRace(rs.getString(3));
                battlegroundCharacterModelDAL.setRole(rs.getString(4));
                battlegroundCharacterModelDAL.setHp(level);
                battlegroundCharacterModelDAL.setMana(level);
                battlegroundCharacterModelDAL.setArmor(0);
                battlegroundCharacterModelDAL.setStrength(level);
                battlegroundCharacterModelDAL.setAgility(level);
                battlegroundCharacterModelDAL.setIntelligence(level);
                return battlegroundCharacterModelDAL;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getCharacterNameById(int char_id) {

        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT " + CHARACTERS_NAME + " FROM " + TABLE_CHARACTERS + " WHERE _id=" + char_id);
            if(rs.next()){
                return rs.getString(CHARACTERS_NAME);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCharacterGold(int char_id){
        try(Connection conn = dataSource.getConnection()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT " + CHARACTERS_GOLD + " FROM " + TABLE_CHARACTERS + " WHERE _id=" + char_id);
            if(rs.next()){
                return rs.getInt(CHARACTERS_GOLD);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void reduceCharacterGold(int char_id, int item_price, int char_gold){
        int remaining_gold = char_gold - item_price;
        try(Connection conn = dataSource.getConnection()){
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE " + TABLE_CHARACTERS + " SET " + CHARACTERS_GOLD +
                    "=" + remaining_gold + " WHERE _id=" + char_id);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

}
