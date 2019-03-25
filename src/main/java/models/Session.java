package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static resources.Cons.*;

public class Session {
    boolean existingSession;
    long userId;

    public Session(String session, Connection connection) throws SQLException {
        PreparedStatement searchForSession = connection.prepareStatement("SELECT * FROM " + TABLE_SESSIONS + " WHERE " + SESSIONS_HASHED_SESSION + "=?");
        searchForSession.setString(1, session);
        System.out.println(session);
        ResultSet rs = searchForSession.executeQuery();
        if(rs.next()){
            existingSession = true;
            userId = rs.getLong(SESSIONS_USER_ID);
        } else {
            this.existingSession = false;
            this.userId = NO_ID;
        }
    }

    public boolean isExistingSession() {
        return existingSession;
    }

    public void setExistingSession(boolean existingSession) {
        this.existingSession = existingSession;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
