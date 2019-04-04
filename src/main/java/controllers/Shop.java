package controllers;

import dao.*;
import models.Character;
import models.CustomCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static resources.Cons.NO_ID;

@RequestMapping("/shop")
@Controller
public class Shop {

    @Autowired
    UsersDao usersDao;

    @Autowired
    SessionsDao sessionsDao;

    @Autowired
    MsgDao msgDao;

    @Autowired
    TablesDao tablesDao;

    @Autowired
    CharacterDao characterDao;

    @Autowired
    DataSource dataSource;

    @GetMapping
    public String getItems(Map<String, Object> model, @CookieValue(value= "sessionID", defaultValue = "0") String session){
            int characterId = usersDao.getCharacterIdFromUserId(userId);
            List<Character> characters = characterDao.getAllCharactersExceptYourself(characterId);
            List<CustomCharacter> customCharacters = characterDao.formCustomCharacterModel(characters);
            model.put("list", customCharacters);

            return "fight";
        }
        return "redirect:/";
    }

}
