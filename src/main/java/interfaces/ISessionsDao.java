package interfaces;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.http.HttpServletResponse;

public interface ISessionsDao {

    void setSessionsTemplate(JdbcTemplate sessionsTemplate);
    void save(int userId, HttpServletResponse response);
    boolean checkExistingSession(String session);
    int getUserIdFromSession(String session);
    String getUserNameFromSession(int userId);
}
