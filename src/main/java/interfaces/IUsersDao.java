package interfaces;

import models.dal.UserDAL;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface IUsersDao {

    void setUsersTemplate(JdbcTemplate usersTemplate);
    int save(String username, String email, String pass);
    int updateUserWithCharacterId(int userId, int characterId);
    int getCharacterIdFromUserId(int user_id);
    List<UserDAL> getUsersByUsernameOrEmail(String username, String email);
    UserDAL getUserByUsername(String username);

}
