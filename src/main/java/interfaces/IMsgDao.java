package interfaces;

import models.dal.MessageDAL;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public interface IMsgDao {

    void setMsgTemplate(JdbcTemplate msgTemplate);
    void save(int charID, Date msgDate, String msgText);
    List<MessageDAL> getMessages();
}
