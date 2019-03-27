package controllers;

import dao.CharacterDao;
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

@RequestMapping("/fighterselection")
@Controller
public class FighterSelection {

    @Autowired
    CharacterDao characterDao;

    @Autowired
    SessionsDao sessionsDao;

    @Autowired
    UsersDao usersDao;

    @GetMapping
    public String getFighters(Map<String, Object> model, @CookieValue (value= "sessionID", defaultValue = "0") String session){
        int userId = sessionsDao.getUserIdFromSession(session);
        if (userId != NO_ID) {
            if (usersDao.getCharacterIdFromUserId(userId) == 0) {
                return "characterCreation";
            }
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            List<Character> characters = characterDao.getAllCharactersExceptYourself(characterId);
            List<CustomCharacter> customCharacters = characterDao.formCustomCharacterModel(characters);
            model.put("list", customCharacters);
            return "fight";
        }
        return "redirect:/";
    }
}
