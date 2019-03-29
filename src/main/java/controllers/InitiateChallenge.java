package controllers;

import dao.ChallengesDao;
import dao.CharacterDao;
import dao.SessionsDao;
import dao.UsersDao;
import models.Character;
import models.CustomCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static resources.Cons.NO_ID;

@RequestMapping("/initiateChallenge")
@Controller
public class InitiateChallenge {

    @Autowired
    CharacterDao characterDao;

    @Autowired
    SessionsDao sessionsDao;

    @Autowired
    UsersDao usersDao;

    @Autowired
    ChallengesDao challengesDao;

    @GetMapping
    public String getChallenge() {
        return "redirect:/";
    }

    @PostMapping
    public String postChallenge(@ModelAttribute CustomCharacter customCharacter, @CookieValue (value="sessionID", defaultValue = "0") String session){
        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "redirect:/create";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            int challengedCharacterId = characterDao.getCharacterIdFromCharacterName(customCharacter.name);
            challengesDao.insertChallenge(characterId, challengedCharacterId);
        }
        return "redirect:/";
    }
}
