package controllers;

import dao.CharacterDao;
import dao.MsgDao;
import dao.SessionsDao;
import dao.UsersDao;
import models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static resources.Cons.NO_ID;

@RequestMapping("/messages")
@Controller
public class MsgController {

    @Autowired
    MsgDao msgDao;

    @Autowired
    UsersDao usersDao;

    @Autowired
    CharacterDao characterDao;

    @Autowired
    SessionsDao sessionsDao;

    @PostMapping
    public String postMsgController(@ModelAttribute Message message, @CookieValue(value = "sessionID", defaultValue = "0") String session){
        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "characterCreation";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            msgDao.save(characterId, message.getMsg_text());

            return "loggedIn";
        }
        return "redirect:/";
    }
}
