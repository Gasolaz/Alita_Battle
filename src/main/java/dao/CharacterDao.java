package dao;

import models.BattlegroundCharacterModel;
import models.Character;
import models.CustomCharacter;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static resources.Cons.*;
import static resources.Cons.CHARACTERS_NAME;

public class CharacterDao {

    @Autowired
    DataSource dataSource;

    JdbcTemplate characterTemplate;

    public void setCharacterTemplate(JdbcTemplate characterTemplate) {
        this.characterTemplate = characterTemplate;
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
            statement.executeUpdate("INSERT INTO Characters(character_name, race_id, role_id, sex, level, wins, loses, gold)" +
                    " VALUES('" + name + "', " + raceId + ", " + roleId + ", '" + gender + "', 1, 0, 0, 0)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Character> getAllCharactersExceptYourself(int characterId) {
        String sql = "select * from Characters WHERE _id!=" + characterId;
        return characterTemplate.query(sql, new RowMapper<Character>() {
            public Character mapRow(ResultSet rs, int row) throws SQLException {
                Character character = new Character();
                character.set_id(rs.getInt(1));
                character.setCharacter_name(rs.getString(2));
                character.setRace(Integer.toString(rs.getInt(3)));
                character.setRole(Integer.toString(rs.getInt(4)));
                character.setSex(rs.getString(5));
                character.setLevel(rs.getInt(6));
                character.setWins(rs.getInt(7));
                character.setLoses(rs.getInt(8));
                character.setGold(rs.getInt(9));
                return character;
            }
        });
    }

    public List<CustomCharacter> formCustomCharacterModel(List<Character> characters) {
        List<CustomCharacter> customCharacters = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            for (Character character : characters) {
                String sql = "SELECT ra.race_name, ro.role FROM Characters AS ch INNER JOIN Races AS ra ON ch.race_id=ra._id " +
                        "INNER JOIN Roles AS ro ON ch.role_id=ro._id WHERE ch._id=" + character.get_id();
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql);

                while(rs.next()){
                    CustomCharacter customCharacter = new CustomCharacter();
                    customCharacter.setName(character.getCharacter_name());
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

    public BattlegroundCharacterModel formBattlegroundCharacterModelFromCharacterId( int characterId){
        try (Connection conn = dataSource.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT cha.character_name, cha.level, ra.race_name, ro.role FROM Characters AS cha INNER JOIN Races AS ra " +
                    "ON ra._id=cha.race_id INNER JOIN Roles AS ro ON ro._id=cha.role_id WHERE cha._id=" + characterId);
            if(rs.next()){
                int level = rs.getInt(2);
                BattlegroundCharacterModel battlegroundCharacterModel = new BattlegroundCharacterModel();
                battlegroundCharacterModel.setName(rs.getString(1));
                battlegroundCharacterModel.setLevel(level);
                battlegroundCharacterModel.setRace(rs.getString(3));
                battlegroundCharacterModel.setRole(rs.getString(4));
                battlegroundCharacterModel.setHp(level);
                battlegroundCharacterModel.setMana(level);
                battlegroundCharacterModel.setArmor(0);
                battlegroundCharacterModel.setStrength(level);
                battlegroundCharacterModel.setAgility(level);
                battlegroundCharacterModel.setIntelligence(level);
                return battlegroundCharacterModel;
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

}
