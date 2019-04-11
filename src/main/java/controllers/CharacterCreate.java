package controllers;

import dao.CharacterDao;
import interfaces.ICharacterDao;
import interfaces.ISessionsDao;
import interfaces.IUsersDao;
import dao.SessionsDao;
import models.bl.CharacterFormModelBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static resources.Cons.NO_ID;

@Controller
public class CharacterCreate {

    @Autowired
    ISessionsDao sessionsDao;

    @Autowired
    IUsersDao usersDao;

    @Autowired
    ICharacterDao characterDao;

    @GetMapping("/create")
    public String getCreate(Map<String, Object> model, @CookieValue(value = "sessionID", defaultValue = "0") String session) {
        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "characterCreation";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/create")
    public String postCreate(Map<String, Object> model, @CookieValue(value = "sessionID", defaultValue = "0") String session,
                             @ModelAttribute CharacterFormModelBL cfm){
        if(!characterDao.isUsernameAlreadyTaken(cfm.name)){
            int userId = sessionsDao.getUserIdFromSession(session);
            characterDao.save(cfm.race, cfm.role, cfm.gender, cfm.name);
            int characterId = characterDao.selectCharacterIdByCharacterName(cfm.name);
            usersDao.updateUserWithCharacterId(userId, characterId);
            return "redirect:/AlitaBattle";
        }
        model.put("error", "Username already taken");
        return "characterCreation";
    }
}
