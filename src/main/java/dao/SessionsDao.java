package dao;

import models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import static dao.UsersDao.*;

public class SessionsDao {

    @Autowired
    DataSource dataSource;

    JdbcTemplate sessionsTemplate;

    public void setSessionsTemplate(JdbcTemplate sessionsTemplate) {
        this.sessionsTemplate = sessionsTemplate;
    }

    public void save(int userId, HttpServletResponse response) {
        try {
            String sessionID = new Random().nextLong() + "";
            byte[] salt = getSalt();
            String generatedSession = get_SHA_256_SecurePassword(sessionID, salt);
            sessionsTemplate.update("INSERT INTO Sessions(hashed_session, salt, user_id) VALUES('" + generatedSession
                    + "', '" + fromByteArrayToString(salt) + "', '" + userId + "')");
            Cookie cookie = new Cookie("sessionID", generatedSession);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(cookie);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public boolean checkExistingSession(String session) {
        String sql = "SELECT * FROM Sessions WHERE hashed_session='" + session + "'";
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;

    }
}
