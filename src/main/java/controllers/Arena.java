package controllers;

import interfaces.*;
import models.dal.BattlegroundCharacterModelDAL;
import models.bl.CustomCharacterBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static resources.ConsTables.NO_ID;

@RequestMapping("")
@Controller
public class Arena {

    @Autowired
    ISessionsDao sessionsDao;

    @Autowired
    IUsersDao usersDao;

    @Autowired
    ICharacterDao characterDao;

    @Autowired
    IArenaDao arenaDao;

    @Autowired
    IChallegesDao challengesDao;

    @GetMapping("/arena")
    public String getArena(Map<String, Object> model, @CookieValue(value = "sessionID", defaultValue = "0") String session) {

        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "redirect:/create";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            String characterName = characterDao.getCharacterNameById(characterId);
            List<CustomCharacterBL> charactersWhoFightWithYou = arenaDao.selectFightsByCharacterId(characterId);
            model.put("list", charactersWhoFightWithYou);
            model.put("characterName", characterName); // (L) add to model 'characterName'

            return "arena";
        }
        return "redirect:/";
    }

    @PostMapping("/arena")
    public String postArena(@ModelAttribute CustomCharacterBL customCharacter, Map<String, Object> model, @CookieValue(value = "sessionID", defaultValue = "0") String session) {

        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "redirect:/create";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            int enemyId = characterDao.getCharacterIdFromCharacterName(customCharacter.name);
            BattlegroundCharacterModelDAL yourModel = characterDao.formBattlegroundCharacterModelFromCharacterId(characterId);
            BattlegroundCharacterModelDAL enemyModel = characterDao.formBattlegroundCharacterModelFromCharacterId(enemyId);

            model.put("yourImage", characterDao.getImageLink(characterId));
            model.put("enemyImage", characterDao.getImageLink(enemyId));
            String result = arenaDao.checkIfResultIsEmpty(characterId, enemyId);
//            System.out.println("result = " + result);
            if (result != null) {
                return returnFightResultPage(result, characterId, enemyId, model, yourModel, enemyModel);
            }

            if (!arenaDao.checkIfYouMadeADecision(characterId, enemyId)) {
                yourModel.setTempHp(characterDao.getTempHpForBattlegroundCharacterModel(characterId, enemyId));
                enemyModel.setTempHp(characterDao.getTempHpForBattlegroundCharacterModel(enemyId, characterId));
                model.put("yourModel", yourModel);
                model.put("enemyModel", enemyModel);
                return "fightingPage";
            }
            if (arenaDao.checkIfBothCharactersMadeADecision(characterId, enemyId)) {
                arenaDao.resolveFight(characterId, enemyId);
                result = arenaDao.checkIfResultIsEmpty(characterId, enemyId);
//                System.out.println("result = " + result);
                yourModel.setTempHp(characterDao.getTempHpForBattlegroundCharacterModel(characterId, enemyId));
                enemyModel.setTempHp(characterDao.getTempHpForBattlegroundCharacterModel(enemyId, characterId));
                model.put("yourModel", yourModel);
                model.put("enemyModel", enemyModel);
                if (result != null) {
                    return returnFightResultPage(result, characterId, enemyId, model, yourModel, enemyModel);
                }
                return "fightingPage";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/acceptChallenge")
    public String postAcceptChallenge(@ModelAttribute CustomCharacterBL customCharacter, Map<String, Object> model, @CookieValue(value = "sessionID", defaultValue = "0") String session) {

        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "redirect:/create";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            int enemyId = characterDao.getCharacterIdFromCharacterName(customCharacter.name);
            arenaDao.insertPlayerToArena(characterId, enemyId);

            // Drop from Challenged from Challenges
            challengesDao.dropChallenged(characterId, enemyId);

            return "redirect:/challenge"; //redirect to challenges
        }
        return "redirect:/";
    }

    public String returnFightResultPage(String result, int characterId, int enemyId, Map<String, Object> model,
                                        BattlegroundCharacterModelDAL yourModel, BattlegroundCharacterModelDAL enemyModel) {
        if (result.equals("win") || result.equals("lose") || result.equals("draw")) {
            characterDao.updateCharacterAccordingToResult(characterId, result);
            arenaDao.deleteFightAndArenaForYou(characterId, enemyId);
        }
        switch (result) {
            case "win":
                model.put("matchResult", yourModel.name + " have won vs. " + enemyModel.name);
                break;
            case "draw":
                model.put("matchResult", yourModel.name + " have drawn vs. " + enemyModel.name);
                break;
            case "lose":
                model.put("matchResult", yourModel.name + " have lost vs " + enemyModel.name);
                break;
        }
        return "fightResult";
    }
}
