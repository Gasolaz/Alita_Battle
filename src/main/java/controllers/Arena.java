package controllers;

import dao.*;
import models.BattlegroundCharacterModel;
import models.CustomCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/arena")
    public String getArena(Map<String, Object> model, @CookieValue(value = "sessionID", defaultValue = "0") String session) {

        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "redirect:/create";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            List<CustomCharacter> charactersWhoFightWithYou = arenaDao.selectFightsByCharacterId(characterId);
            model.put("list", charactersWhoFightWithYou);

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
            if (arenaDao.checkIfResultIsEmpty(characterId, enemyId) != null) {
                String result = arenaDao.checkIfResultIsEmpty(characterId, enemyId);
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
            if (!arenaDao.checkIfYouMadeADecision(characterId, enemyId)) {
                return "fightingPage";
            }
            if (arenaDao.checkIfBothCharactersMadeADecision(characterId, enemyId)) {
                int i = arenaDao.resolveFight(characterId, enemyId);
                if (i == 1) {
                    model.put("matchResult", yourModel.name + " have won vs. " + enemyModel.name);
                    return "fightResult";
                } else if (i == 3) {
                    model.put("matchResult", yourModel.name + " draw vs. " + enemyModel.name);
                    return "fightResult";
                } else if (i == 2) {
                    model.put("matchResult", yourModel.name + " have lost vs " + enemyModel.name);
                    return "fightResult";
                }

                return "fightingPage";
            }
//            return "redirect:/waiting";
        }
        return "redirect:/";
    }

    @PostMapping("/acceptChallenge")
    public String postAcceptChallenge(@ModelAttribute CustomCharacter
                                              customCharacter, Map<String, Object> model, @CookieValue(value = "sessionID", defaultValue = "0") String
                                              session) {

        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "redirect:/create";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            int enemyId = characterDao.getCharacterIdFromCharacterName(customCharacter.name);
            arenaDao.insertPlayerToArena(characterId, enemyId);

            return "redirect:/arena";
        }
        return "redirect:/";
    }
}
