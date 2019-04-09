package controllers;

import dao.*;
import models.BattlegroundCharacterModel;
import models.CustomCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static resources.Cons.NO_ID;

@RequestMapping("")
@Controller
public class Arena {

    @Autowired
    SessionsDao sessionsDao;

    @Autowired
    UsersDao usersDao;

    @Autowired
    CharacterDao characterDao;

    @Autowired
    ArenaDao arenaDao;

    @Autowired
    ChallengesDao challengesDao;

    @GetMapping("/arena")
    public String getArena(Map<String, Object> model, @CookieValue(value = "sessionID", defaultValue = "0") String session) {

        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "redirect:/create";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            String characterName = characterDao.getCharacterNameById(characterId);
            List<CustomCharacter> charactersWhoFightWithYou = arenaDao.selectFightsByCharacterId(characterId);
            model.put("list", charactersWhoFightWithYou);
            model.put("characterName", characterName); // (L) add to model 'characterName'

            return "arena";
        }
        return "redirect:/";
    }

    @PostMapping("/arena")
    public String postArena(@ModelAttribute CustomCharacter customCharacter, Map<String, Object> model, @CookieValue(value = "sessionID", defaultValue = "0") String session) {

        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "redirect:/create";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            int enemyId = characterDao.getCharacterIdFromCharacterName(customCharacter.name);
            BattlegroundCharacterModel yourModel = characterDao.formBattlegroundCharacterModelFromCharacterId(characterId);
            BattlegroundCharacterModel enemyModel = characterDao.formBattlegroundCharacterModelFromCharacterId(enemyId);
            model.put("yourModel", yourModel);
            model.put("enemyModel", enemyModel);
            String result = arenaDao.checkIfResultIsEmpty(characterId, enemyId);
//            System.out.println("result = " + result);
            if (result != null) {
                return returnFightResultPage(result, characterId, enemyId, model, yourModel, enemyModel);
            }

            if (!arenaDao.checkIfYouMadeADecision(characterId, enemyId)) {
                return "fightingPage";
            }
            if (arenaDao.checkIfBothCharactersMadeADecision(characterId, enemyId)) {
                arenaDao.resolveFight(characterId, enemyId);
                result = arenaDao.checkIfResultIsEmpty(characterId, enemyId);
//                System.out.println("result = " + result);
                if (result != null) {
                    return returnFightResultPage(result, characterId, enemyId, model, yourModel, enemyModel);
                }
                return "fightingPage";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/acceptChallenge")
    public String postAcceptChallenge(@ModelAttribute CustomCharacter customCharacter, Map<String, Object> model, @CookieValue(value = "sessionID", defaultValue = "0") String session) {

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
                                 BattlegroundCharacterModel yourModel, BattlegroundCharacterModel enemyModel) {
        if (result.equals("win") || result.equals("lose") || result.equals("draw")) {
            characterDao.updateCharacterAccordingToResult(characterId, result);
            arenaDao.deleteFightAndArenaForYou(characterId, enemyId);
        }
        switch (result) {
            case "win":
                model.put("matchResult", yourModel.name + " have won vs. " + enemyModel.name);
                break;
            case "draw":
                model.put("matchResult", yourModel.name + " draw vs. " + enemyModel.name);
                break;
            case "lose":
                model.put("matchResult", yourModel.name + " have lost vs " + enemyModel.name);
                break;
        }
        return "fightResult";
    }
}
