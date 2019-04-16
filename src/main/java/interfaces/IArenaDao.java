package interfaces;

import models.bl.CustomCharacterBL;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface IArenaDao {

    void setAreneTemplate(JdbcTemplate areneTemplate);
    String checkIfResultIsEmpty(int characterId, int enemyId);
    boolean deleteFightAndArenaForYou(int characterId, int enemyId);
    void resolveFight(int characterId, int enemyId);
    boolean checkIfBothCharactersMadeADecision(int characterId, int enemyId);
    boolean checkIfYouMadeADecision(int characterId, int enemyId);
    void insertMatchResults(int character_id, int enemy_id, String attack, String defend);
    void insertPlayerToArena(int characterId, int enemyId);
    List<CustomCharacterBL> selectFightsByCharacterId(int characterId);
//    public int[] countLevelByExp (int characterId);
}
