package dao;


import org.springframework.jdbc.core.JdbcTemplate;

public class UsersDao {
    JdbcTemplate usersTemplate;

    public void setUsersTemplate(JdbcTemplate usersTemplate) {
        this.usersTemplate = usersTemplate;
    }



}
