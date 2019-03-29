package controllers;

import dao.ChallengesDao;
import dao.SessionsDao;
import dao.UsersDao;
import models.Character;
import models.CustomCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

import static resources.Cons.NO_ID;

@RequestMapping("/challenge")
@Controller
public class Challenge {

    @Autowired
    UsersDao usersDao;

    @Autowired
    SessionsDao sessionsDao;

    @Autowired
    ChallengesDao challengesDao;

    @GetMapping
    public String getChallenge(Map<String, Object> model, @CookieValue (value = "sessionID", defaultValue = "0") String session){
        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "redirect:/create";
            }
            List<CustomCharacter> charactersWhoChallengeYou = challengesDao.getAllChallengesForYou(userId);
            model.put("list", charactersWhoChallengeYou);
            return "challenge";
        }
        return "redirect:/";
    }
}
