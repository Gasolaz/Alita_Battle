package controllers;

import dao.ArenaDao;
import dao.CharacterDao;
import dao.SessionsDao;
import dao.UsersDao;
import models.AttackDefendBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static resources.Cons.NO_ID;

@RequestMapping("/fightResult")
@Controller
public class FightResults {

    @Autowired
    ArenaDao arenaDao;

    @Autowired
    SessionsDao sessionsDao;

    @Autowired
    UsersDao usersDao;

    @Autowired
    CharacterDao characterDao;

    @PostMapping
    public String fightOpponent (@ModelAttribute AttackDefendBL attackDefendBL, Map<String, Object > model, @CookieValue (value = "sessionID", defaultValue = "0") String session){

        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "redirect:/create";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            int enemyId = characterDao.getCharacterIdFromCharacterName(attackDefendBL.enemyName);
            arenaDao.insertMatchResults(characterId, enemyId, attackDefendBL.attack, attackDefendBL.defence);
        }
        return "redirect:/";
    }
}
