package interfaces;

import models.bl.ItemBL;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IItemDao {

    void setItemTemplate(JdbcTemplate itemTemplate);
    void insertToInventory(int user_id, int char_id, String item_name);
    int selectEmptyFieldIndex(String columnname, int user_id, int char_id, Connection conn) throws SQLException;
    boolean isEmptyField(String columnname, int index, int user_id, int char_id, Connection conn) throws SQLException;
    void insertUserAndCharToInventory(int user_id, int char_id);
    List<ItemBL> getItems(String tablename);

}
