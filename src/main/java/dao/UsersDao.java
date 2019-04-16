package dao;


import interfaces.IUsersDao;
import models.dal.UserDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static resources.ConsTables.NO_ID;

public class UsersDao implements IUsersDao {
    JdbcTemplate usersTemplate;

    @Autowired
    DataSource dataSource;

    public void setUsersTemplate(JdbcTemplate usersTemplate) {
        this.usersTemplate = usersTemplate;
    }

    public int save(String username, String email, String pass) {
        try {
            byte[] salt = getSalt();
            String generatedPass = get_SHA_256_SecurePassword(pass, salt);
            return usersTemplate.update("INSERT INTO Users(username, hashed_pass, salt, email, isAdmin) VALUES('" + username
                    + "', '" + generatedPass + "', '" + fromByteArrayToString(salt) + "', '" + email.toLowerCase() + "', 0)");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int updateUserWithCharacterId(int userId, int characterId){
        String sql = "UPDATE Users SET character_id=" + characterId + " WHERE _id=" + userId;
        return usersTemplate.update(sql);
    }

    public int getCharacterIdFromUserId(int user_id) {
        String sql = "SELECT * FROM Users WHERE _id = '" + user_id + "'";
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("character_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return NO_ID;
    }

//    public int update(UserDAL user) {
//        String sql = "update Emp99 set name='" + p.getName() + "', salary=" + p.getSalary() + ",designation='" + p.getDesignation() + "' where id=" + p.getId() + "";
//        return template.update(sql);
//    }

//    public int delete(int id) {
//        String sql = "delete from Emp99 where id=" + id + "";
//        return template.update(sql);
//    }

    public List<UserDAL> getUsersByUsernameOrEmail(String username, String email){
        String sql = "select * from Users where lower(username)='" + username.toLowerCase() + "' OR lower(email)='" + email.toLowerCase() + "'";
        return usersTemplate.query(sql, new RowMapper<UserDAL>() {
            public UserDAL mapRow(ResultSet rs, int row) throws SQLException {
                UserDAL user = new UserDAL();
                user.set_id(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setHashed_pass(rs.getString(3));
                user.setSalt(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setIsAdmin(rs.getInt(7));
                return user;
            }
        });
    }

    public UserDAL getUserByUsername(String username){
        String sql = "select * from Users where username='" + username + "'";
        return usersTemplate.queryForObject(sql, new BeanPropertyRowMapper<UserDAL>(UserDAL.class));
    }

//    public List<Emp> getEmployees() {
//        return template.query("select * from Emp99", new RowMapper<Emp>() {
//            public Emp mapRow(ResultSet rs, int row) throws SQLException {
//                Emp e = new Emp();
//                e.setId(rs.getInt(1));
//                e.setName(rs.getString(2));
//                e.setSalary(rs.getFloat(3));
//                e.setDesignation(rs.getString(4));
//                return e;
//            }
//        });
//    }

    static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            generatedPassword = fromByteArrayToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    static String fromByteArrayToString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
