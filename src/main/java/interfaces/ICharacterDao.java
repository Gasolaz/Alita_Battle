package interfaces;

import models.bl.CustomCharacterBL;
import models.dal.BattlegroundCharacterModelDAL;
import models.dal.CharacterDAL;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface ICharacterDao {

    void setCharacterTemplate(JdbcTemplate characterTemplate);
    boolean isUsernameAlreadyTaken(String name);
    void save(String race, String role, String gender, String name);
    void updateCharacterAccordingToResult (int characterId, String result);
    List<CharacterDAL> getAllCharactersExceptYourself(int characterId);
    List<CustomCharacterBL> formCustomCharacterModel(List<CharacterDAL> characterDALS);
    int getCharacterIdFromCharacterName(String name);
    int selectCharacterIdByCharacterName(String name);
    BattlegroundCharacterModelDAL formBattlegroundCharacterModelFromCharacterId(int characterId);
    String getCharacterNameById(int char_id);
    String getImageLink(int characterId);
    int getCharacterGold(int char_id);
    void reduceCharacterGold(int char_id, int item_price, int char_gold);
    int getTempHpForBattlegroundCharacterModel(int characterId, int enemyId);
    int[] displayResultInLoggedIn (int character_id);
    int[] countLevelByExp(int character_id);
    int getGold (int characterId);
}
