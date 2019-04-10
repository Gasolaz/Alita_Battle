package controllers;

import dao.CharacterDao;
import interfaces.ICharacterDao;
import interfaces.ISessionsDao;
import interfaces.IUsersDao;
import dao.SessionsDao;
import models.dal.CharacterDAL;
import models.bl.CustomCharacterBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

import static resources.Cons.NO_ID;

@RequestMapping("/fighterselection")
@Controller
public class FighterSelection {

    @Autowired
    ICharacterDao characterDao;

    @Autowired
    ISessionsDao sessionsDao;

    @Autowired
    IUsersDao usersDao;

    @GetMapping
    public String getFighters(Map<String, Object> model, @CookieValue (value= "sessionID", defaultValue = "0") String session){
        int userId = sessionsDao.getUserIdFromSession(session);
        String userName = sessionsDao.getUserNameFromSession(userId); // (L) get 'userName' from ID

        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "redirect:/create";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            List<CharacterDAL> characterDALS = characterDao.getAllCharactersExceptYourself(characterId);
            List<CustomCharacterBL> customCharacters = characterDao.formCustomCharacterModel(characterDALS);
            String characterName = characterDao.getCharacterNameById(characterId);
            model.put("list", customCharacters);
            model.put("characterName", characterName); // (L) add to model 'characterName'

            return "fight";
        }
        return "redirect:/";
    }
}
