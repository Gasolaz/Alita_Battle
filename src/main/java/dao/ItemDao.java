package dao;

import interfaces.IItemDao;
import interfaces.IUsersDao;
import models.bl.ItemBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static resources.ConsItems.*;
import static resources.ConsTables.*;

public class ItemDao implements IItemDao {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate itemTemplate;

    @Autowired
    IUsersDao usersDao;

    public void setItemTemplate(JdbcTemplate itemTemplate) {
        this.itemTemplate = itemTemplate;
    }

    public ItemDao(){
    }

//    public void insertToInventory(int user_id, int char_id, String item_name){
//        try(Connection conn = dataSource.getConnection()){
//            int column_index = selectEmptyFieldIndex(ITEM_NAME, user_id, char_id, conn);
//            //String sql = "UPDATE " + TABLE_INVENTORY + " SET item_name" + "?" + " = ? WHERE user_id = ? AND character_id = ?";
//            PreparedStatement ps = conn.prepareStatement(INSERT_ITEM_TO_INVENTORY);
//            ps.setInt(1, column_index);
//            ps.setString(2, item_name);
//            ps.setInt(3, user_id);
//            ps.setInt(4, char_id);
//            ps.executeUpdate();
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void insertItemToInventory(String item_type, int char_id, int item_id){
        try(Connection conn = dataSource.getConnection()){
            String columname = getColumnName(item_type);
            String sql = "UPDATE " + TABLE_CHARACTERS + " SET " + columname + "=" + item_id + " WHERE _id=" + char_id;
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public String getColumnName(String item_type){
        if(item_type.equals("left_hand_items")){
            return LEFT_HAND_ID;
        }
        else if(item_type.equals("right_hand_items")){
            return RIGHT_HAND_ID;
        }
        else if(item_type.equals("torso_items")){
            return TORSO_ID;
        }
        else return LEGS_ID;
    }

    public List<ItemBL> getItems(String tablename){
        List<ItemBL> items = new ArrayList<>();
        try(Connection conn = dataSource.getConnection()){
            String sql = build_sql_statement(tablename, ID, ITEM_NAME, PRICE, TABLE_ATTRIBUTES, ATTRIBUTE_ID, ID);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
//            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()){
                if(isViableItem(rs.getString(ITEM_NAME))){
                    items.add(new ItemBL(rs.getInt(ID), rs.getString(ITEM_NAME), rs.getInt(PRICE),
                            rs.getInt(STRENGTH), rs.getInt(AGILITY), rs.getInt(INTELLIGENCE),
                            rs.getInt(DEFENSE), rs.getInt(HITPOINTS)));
                }
            }
//            System.out.println(rsmd.getColumnName(1) + " " + rsmd.getColumnName(2) + " " + rsmd.getColumnName(3) + " " + rsmd.getColumnName(4) + " " +
////                    rsmd.getColumnName(5) + " " + rsmd.getColumnName(6) + " " + rsmd.getColumnName(7) + " " + rsmd.getColumnName(8));
        } catch(SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    private String build_sql_statement(String item_table, String item_id, String item_name, String price, String attribute_table, String attribute_id, String _id){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(item_table);
        sb.append(".");
        sb.append(item_id);
        sb.append(", ");
        sb.append(item_table);
        sb.append(".");
        sb.append(item_name);
        sb.append(", ");
        sb.append(item_table);
        sb.append(".");
        sb.append(price);
        sb.append(", ");
        sb.append(attribute_table);
        sb.append(".* FROM ");
        sb.append(item_table);
        sb.append(" INNER JOIN ");
        sb.append(attribute_table);
        sb.append(" ON ");
        sb.append(item_table);
        sb.append(".");
        sb.append(attribute_id);
        sb.append(" = ");
        sb.append(attribute_table);
        sb.append(".");
        sb.append(_id);

        return sb.toString();
    }

    private boolean isViableItem(String item_name){
        if(item_name.equals("empty") || item_name.equals("future item") || item_name.equals("peasant shirt") || item_name.equals("peasant trousers")){
            return false;
        }
        return true;
    }

}
