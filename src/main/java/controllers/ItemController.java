package controllers;

import dao.CharacterDao;
import dao.ItemDao;
import dao.SessionsDao;
import dao.UsersDao;
import models.bl.CharacterFormModelBL;
import models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static resources.Cons.*;

@RequestMapping("/shop")
@Controller
public class ItemController {

    @Autowired
    ItemDao itemDao;

    @Autowired
    SessionsDao sessionsDao;

    @Autowired
    UsersDao usersDao;

    @Autowired
    CharacterDao characterDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("first_hand_items", itemDao.getItems(TABLE_FIRST_HAND));
        modelMap.put("second_hand_items", itemDao.getItems(TABLE_SECOND_HAND));
        modelMap.put("torso_items", itemDao.getItems(TABLE_TORSO));
        modelMap.put("leg_items", itemDao.getItems(TABLE_LEGS));
        return "shop";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postCreate(ModelMap modelMap, @ModelAttribute("item") Item item, @CookieValue(value = "sessionID", defaultValue = "0") String session){
        int userId = sessionsDao.getUserIdFromSession(session);
        int charId = usersDao.getCharacterIdFromUserId(userId);
        int item_price = item.getPrice();
        int char_gold = characterDao.getCharacterGold(charId);
        if (char_gold - item_price >= 0){
            itemDao.insertToInventory(userId, charId, item.getName());
            characterDao.reduceCharacterGold(charId, item_price, char_gold);
        }
        return index(modelMap);
    }
}
