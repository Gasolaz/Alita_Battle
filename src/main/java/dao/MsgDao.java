package dao;

import models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static resources.Cons.*;

public class MsgDao {

    @Autowired
    DataSource dataSource;

    JdbcTemplate msgTemplate;

    public void setMsgTemplate(JdbcTemplate msgTemplate) { this.msgTemplate = msgTemplate; }

    public void save(int charID, String msgText) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(INSERT_MSG);
            ps.setInt(1, charID);
            ps.setString(2, msgText);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getMessages() {
        List<Message> messagesList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement ps = conn.prepareStatement(SELECT_MESSAGES);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Message message = new Message(rs.getLong(ID), rs.getLong(MESSAGES_CHARACTER_ID), rs.getString(MESSAGES_TEXT));
                messagesList.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messagesList;
    }

}