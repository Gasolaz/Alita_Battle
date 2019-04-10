package controllers;

import dao.*;
import interfaces.IChallegesDao;
import interfaces.ICharacterDao;
import interfaces.ISessionsDao;
import interfaces.IUsersDao;
import models.bl.CustomCharacterBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static resources.Cons.NO_ID;

@RequestMapping("/initiateChallenge")
@Controller
public class InitiateChallenge {

    @Autowired
    ICharacterDao characterDao;

    @Autowired
    ISessionsDao sessionsDao;

    @Autowired
    IUsersDao usersDao;

    @Autowired
    IChallegesDao challengesDao;

    @GetMapping
    public String getChallenge() {
        return "redirect:/";
    }

    @PostMapping
    public String postChallenge(@ModelAttribute CustomCharacterBL customCharacter, @CookieValue (value="sessionID", defaultValue = "0") String session){
        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "redirect:/create";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            int challengedCharacterId = characterDao.getCharacterIdFromCharacterName(customCharacter.name);
            challengesDao.insertChallenge(characterId, challengedCharacterId);
        }
        return "redirect:/fighterselection";
    }
}
