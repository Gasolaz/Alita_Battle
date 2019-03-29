package dao;

import models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import sun.util.calendar.LocalGregorianCalendar;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static resources.Cons.*;

public class MsgDao {

    @Autowired
    DataSource dataSource;

    JdbcTemplate msgTemplate;

    public void setMsgTemplate(JdbcTemplate msgTemplate) { this.msgTemplate = msgTemplate; }

    public void save(int charID, Date msgDate, String msgText) {

        //java.sql.Date sqlDate = new java.sql.Date(msgDate.getTime());

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(INSERT_MSG);
            ps.setInt(1, charID);
            ps.setTimestamp(2, new Timestamp(msgDate.getTime()));
            ps.setString(3, msgText);
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
                Message message = new Message(rs.getLong(ID), rs.getLong(MESSAGES_CHARACTER_ID), rs.getDate(MESSAGES_TIME), rs.getString(MESSAGES_TEXT));
                messagesList.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messagesList;
    }

}
