package dao;

import models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static resources.Cons.*;

public class ItemDao {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate itemTemplate;

    @Autowired
    UsersDao usersDao;

    public void setItemTemplate(JdbcTemplate itemTemplate) {
        this.itemTemplate = itemTemplate;
    }

    public ItemDao(){
    }

    public void insertToInventory(int user_id, int char_id, String item_name){
        try(Connection conn = dataSource.getConnection()){
            int column_index = selectEmptyFieldIndex(ITEM_NAME, user_id, char_id, conn);
            //String sql = "UPDATE " + TABLE_INVENTORY + " SET item_name" + "?" + " = ? WHERE user_id = ? AND character_id = ?";
            PreparedStatement ps = conn.prepareStatement(INSERT_ITEM_TO_INVENTORY);
            ps.setInt(1, column_index);
            ps.setString(2, item_name);
            ps.setInt(3, user_id);
            ps.setInt(4, char_id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public int selectEmptyFieldIndex(String columnname, int user_id, int char_id, Connection conn) throws SQLException {
        int column_index = 1;
        while(column_index <= MAX_INVENTORY_SPACE){
            if(isEmptyField(columnname, column_index, user_id, char_id, conn)){
                return column_index;
            }
            column_index++;
        }
        return -1;
    }

    public boolean isEmptyField(String columnname, int index, int user_id, int char_id, Connection conn) throws SQLException {
        String sql = "SELECT " + columnname + index + " FROM " + TABLE_INVENTORY + " WHERE user_id=" + user_id + " AND character_id=" + char_id;
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        if(rs.getString(columnname+index)!=null){
            return false;
        }
        return true;
    }

    public void insertUserAndCharToInventory(int user_id, int char_id){
        try(Connection conn = dataSource.getConnection()){
            PreparedStatement ps = conn.prepareStatement(INSERT_TO_INVENTORY);
            ps.setInt(1, user_id);
            ps.setInt(2, char_id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Item> getItems(String tablename){
        List<Item> items = new ArrayList<>();
        try(Connection conn = dataSource.getConnection()){
            String sql = build_sql_statement(tablename, ITEM_ID, ITEM_NAME, PRICE, TABLE_ATTRIBUTES, ID_OF_ATTRIBUTE, ATTRIBUTE_ID);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
//            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()){
                if(isViableItem(rs.getString(ITEM_NAME))){
                    items.add(new Item(rs.getInt(ITEM_ID), rs.getString(ITEM_NAME), rs.getInt(PRICE),
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

    private String build_sql_statement(String item_table, String item_id, String item_name, String price, String attribute_table, String id_of_attribute, String attribute_id){
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
        sb.append(id_of_attribute);
        sb.append(" = ");
        sb.append(attribute_table);
        sb.append(".");
        sb.append(attribute_id);

        return sb.toString();
    }

    private boolean isViableItem(String item_name){
        if(item_name.equals("empty") || item_name.equals("future item") || item_name.equals("peasant shirt") || item_name.equals("peasant trousers")){
            return false;
        }
        return true;
    }

}
