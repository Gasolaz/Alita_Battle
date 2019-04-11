package controllers;

import dao.*;
import interfaces.ICharacterDao;
import interfaces.IMsgDao;
import interfaces.ISessionsDao;
import interfaces.IUsersDao;
import models.dal.MessageDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static resources.Cons.NO_ID;

@RequestMapping("/messages")
@Controller
public class MsgController {

    @Autowired
    IMsgDao msgDao;

    @Autowired
    IUsersDao usersDao;

    @Autowired
    ICharacterDao characterDao;

    @Autowired
    ISessionsDao sessionsDao;

    @PostMapping
    public String postMsgController(@ModelAttribute MessageDAL message, @CookieValue(value = "sessionID", defaultValue = "0") String session){
        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "characterCreation";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            msgDao.save(characterId, new Date(), message.getMsg_text());

            return "redirect:/AlitaBattle";
        }
        return "redirect:/";
    }
}
