package dao;

import interfaces.IMsgDao;
import models.dal.MessageDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static resources.Cons.*;

public class MsgDao implements IMsgDao {

    @Autowired
    DataSource dataSource;

    @Autowired
    CharacterDao characterDao;

    JdbcTemplate msgTemplate;

    public void setMsgTemplate(JdbcTemplate msgTemplate) { this.msgTemplate = msgTemplate; }

    public void save(int charID, Date msgDate, String msgText) {

        //java.sql.Date sqlDate = new java.sql.Date(msgDate.getTime());
        if(msgText.trim().equals("")){
            return;
        }
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

    public List<MessageDAL> getMessages() {
        List<MessageDAL> messagesList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement ps = conn.prepareStatement(SELECT_MESSAGES);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String charName = characterDao.getCharacterNameById(rs.getInt("char_id"));
                MessageDAL message = new MessageDAL(charName, rs.getLong(MESSAGES_CHARACTER_ID), rs.getTimestamp(MESSAGES_TIME), rs.getString(MESSAGES_TEXT));
                messagesList.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messagesList;
    }

}
