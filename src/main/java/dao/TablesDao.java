package dao;

import interfaces.ITablesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;

import static resources.Cons.*;
import static resources.ConsItems.*;


public class TablesDao implements ITablesDao {

    JdbcTemplate tablesTemplate;

    @Autowired
    DataSource dataSource;

    public void setTablesTemplate(JdbcTemplate tablesTemplate) {
        this.tablesTemplate = tablesTemplate;
    }

    public void insertItem(int item_id, String item_name, int price, int attr_id, String insertionSql) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(insertionSql);
            ps.setInt(1, item_id);
            ps.setString(2, item_name);
            ps.setInt(3, price);
            ps.setInt(4, attr_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAttribute(int attr_id, int str_val, int agi_val, int int_val, int def_val, int hp_val, String insertionSql) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(insertionSql);
            ps.setInt(1, attr_id);
            ps.setInt(2, str_val);
            ps.setInt(3, agi_val);
            ps.setInt(4, int_val);
            ps.setInt(5, def_val);
            ps.setInt(6, hp_val);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Creates tables in database Alita_battle if those doesn't exist */
    public void createTables() {

        // Tables insertion to the database 'Alita_battle'
        for(String tablesInsert : tablesCreation.values())
            tablesTemplate.execute(tablesInsert);


        tablesTemplate.execute(INSERT_RACE_DWARF);
        tablesTemplate.execute(INSERT_RACE_ELF);
        tablesTemplate.execute(INSERT_RACE_HUMAN);
        tablesTemplate.execute(INSERT_RACE_ORC);
        tablesTemplate.execute(INSERT_RACE_UNDEAD);

        tablesTemplate.execute(INSERT_ROLE_FIGHTER);
        tablesTemplate.execute(INSERT_ROLE_WIZARD);
        tablesTemplate.execute(INSERT_ROLE_PALADIN);
        tablesTemplate.execute(INSERT_ROLE_ROGUE);



        //Items for main hand (18 items) || attributes no. 1 - 19
        insertItem(1, "empty", 0, 1, INSERT_SET_LEFT_HAND);
        insertItem(2,"club", 5, 2, INSERT_SET_LEFT_HAND);
        insertItem(3, "dagger", 10, 3, INSERT_SET_LEFT_HAND);
        insertItem(4, "short sword", 20, 4, INSERT_SET_LEFT_HAND);
        insertItem(5, "long sword", 25, 5, INSERT_SET_LEFT_HAND);
        insertItem(6, "mace", 20, 6, INSERT_SET_LEFT_HAND);
        insertItem(7, "scimitar", 25, 7, INSERT_SET_LEFT_HAND);
        insertItem(8, "two-handed sword", 35,8, INSERT_SET_LEFT_HAND); //equipped on both hands
        insertItem(9, "shield", 10, 9, INSERT_SET_LEFT_HAND);
        insertItem(10, "whip", 30, 10, INSERT_SET_LEFT_HAND);

        //Items for 2nd hand (17 items - no 2h sword)
        insertItem(1, "empty", 0, 1, INSERT_SET_RIGHT_HAND);
        insertItem(2,"club", 5, 2, INSERT_SET_RIGHT_HAND);
        insertItem(3, "dagger", 10, 3, INSERT_SET_RIGHT_HAND);
        insertItem(4, "short sword", 20, 4, INSERT_SET_RIGHT_HAND);
        insertItem(5, "long sword", 25,5, INSERT_SET_RIGHT_HAND);
        insertItem(6, "mace", 20, 6, INSERT_SET_RIGHT_HAND);
        insertItem(7, "scimitar", 25, 7, INSERT_SET_RIGHT_HAND);
        insertItem(9, "shield", 10, 9, INSERT_SET_RIGHT_HAND);
        insertItem(10, "whip", 30, 10, INSERT_SET_RIGHT_HAND);

        //Items for bottom (10 items) || attributes no. 20 - 29
        insertItem(1, "peasant trousers", 0, 1, INSERT_LEGS);
        insertItem(2, "spiked trousers", 5, 20, INSERT_LEGS);
        insertItem(3, "sexy dress", 1, 21, INSERT_LEGS);
        insertItem(4, "leggings", 8, 22, INSERT_LEGS);
        insertItem(5, "medium platelegs", 15, 23, INSERT_LEGS);
        insertItem(6, "heavy platelegs", 20, 24, INSERT_LEGS);
        insertItem(7, "dragonhide legs", 30, 25, INSERT_LEGS);
        insertItem(8, "legs of speed", 15, 26, INSERT_LEGS);
        insertItem(9, "wizard dress", 15, 27, INSERT_LEGS);
        insertItem(10, "future item", 0, 28, INSERT_LEGS);
        insertItem(11, "future item", 0, 29, INSERT_LEGS);

        //Items for torso (10 items) || attributes no. 30 - 39
        insertItem(1,"peasant shirt", 0, 1, INSERT_TORSO);
        insertItem(2,"lab coat", 10, 30, INSERT_TORSO);
        insertItem(3,"spiked shirt", 5, 31, INSERT_TORSO);
        insertItem(4,"medium plate", 20, 32, INSERT_TORSO);
        insertItem(5,"heavy plate", 40, 33, INSERT_TORSO);
        insertItem(6,"dragonhide armor", 60,34, INSERT_TORSO);
        insertItem(7,"slough of anaconda", 35, 35, INSERT_TORSO);
        insertItem(8,"wizard top robes", 25, 36, INSERT_TORSO);
        insertItem(9,"future item", 0, 37, INSERT_TORSO);
        insertItem(10,"future item", 0, 38, INSERT_TORSO);
        insertItem(11,"future item", 0, 39, INSERT_TORSO);

        //Default attributes
        insertAttribute(1, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //default

        //Hand attributes
        insertAttribute(2, 3, -1, -1, 0, 0, INSERT_ATTRIBUTES); //club
        insertAttribute(3, 1, 3, 0, 0, 0, INSERT_ATTRIBUTES); //dagger
        insertAttribute(4, 2, 2, 0, 0, 0, INSERT_ATTRIBUTES); //short sword
        insertAttribute(5, 3, 1, 0, 0, 0, INSERT_ATTRIBUTES); //long sword
        insertAttribute(6, 7, -1, 0, 0, 0, INSERT_ATTRIBUTES); //mace
        insertAttribute(7, 2, 2, 0, 0, 0, INSERT_ATTRIBUTES); //scimitar
        insertAttribute(8, 10, -3, 0, -2, 0, INSERT_ATTRIBUTES); //2h sword
        insertAttribute(9, 0, -3, 0, 5, 0, INSERT_ATTRIBUTES); //shield
        insertAttribute(10, 4, 4, 0, 0, 0, INSERT_ATTRIBUTES); //whip
        insertAttribute(11, 0, 0, 5, 0, 0, INSERT_ATTRIBUTES); //magic staff
        insertAttribute(12, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item
        insertAttribute(13, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item
        insertAttribute(14, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item
        insertAttribute(15, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item
        insertAttribute(16, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item
        insertAttribute(17, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item
        insertAttribute(18, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item
        insertAttribute(19, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item

        //Legs attributes
        insertAttribute(20, 0, 3, 0, 1, 0, INSERT_ATTRIBUTES); //spiked trousers
        insertAttribute(21, 0, 1, 0, 0, 0, INSERT_ATTRIBUTES); //sexy dress
        insertAttribute(22, 0, 5, 0, 2, 0, INSERT_ATTRIBUTES); //leggings
        insertAttribute(23, 0, -1, 0, 4, 0, INSERT_ATTRIBUTES); //medium platelegs
        insertAttribute(24, 0, -3, 0, 7, 0, INSERT_ATTRIBUTES); //heavy platelegs
        insertAttribute(25, 0, 5, 0, 5, 0, INSERT_ATTRIBUTES); //dragonhide legs
        insertAttribute(26, 0, 8, 0, 1, 0, INSERT_ATTRIBUTES); //legs of speed
        insertAttribute(27, 0, 1, 3, 1, 0, INSERT_ATTRIBUTES); //wizard dress
        insertAttribute(28, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item
        insertAttribute(29, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item

        //Torso attributes
        insertAttribute(30, 0, 1, 5, 1, 0, INSERT_ATTRIBUTES); //lab coat
        insertAttribute(31, 0, 1, 0, 3, 0, INSERT_ATTRIBUTES); //spiked shirt
        insertAttribute(32, 0, -2, 0, 6, 0, INSERT_ATTRIBUTES); //medium plate
        insertAttribute(33, 0, -4, 0, 10, 0, INSERT_ATTRIBUTES); //heavy plate
        insertAttribute(34, 0, 5, 0, 7, 0, INSERT_ATTRIBUTES); //dragonhide armor
        insertAttribute(35, 0, 5, 0, 5, 0, INSERT_ATTRIBUTES); //slough of anaconda
        insertAttribute(36, 0, 0, 7, 1, 0, INSERT_ATTRIBUTES); //wizard top robes
        insertAttribute(37, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item
        insertAttribute(38, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item
        insertAttribute(39, 0, 0, 0, 0, 0, INSERT_ATTRIBUTES); //future item



    }
}
