package interfaces;

import models.bl.CustomCharacterBL;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface IChallegesDao {

    void setChallengeTemplate(JdbcTemplate challengeTemplate);
    void insertChallenge(int characterId, int challengedId);
    List<CustomCharacterBL> getAllChallengesForYou(int userId);
    void dropChallenged(int characterId, int enemyId);
}
