package controllers;

import dao.ItemDao;
import interfaces.ICharacterDao;
import interfaces.ISessionsDao;
import interfaces.IUsersDao;
import models.bl.ItemBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static resources.ConsTables.*;

@RequestMapping("/shop")
@Controller
public class ItemController {

    @Autowired
    ItemDao itemDao;

    @Autowired
    ISessionsDao sessionsDao;

    @Autowired
    IUsersDao usersDao;

    @Autowired
    ICharacterDao characterDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("left_hand_items", itemDao.getItems(TABLE_LEFTHAND));
        modelMap.put("right_hand_items", itemDao.getItems(TABLE_RIGHTHAND));
        modelMap.put("torso_items", itemDao.getItems(TABLE_TORSO));
        modelMap.put("leg_items", itemDao.getItems(TABLE_LEGS));
        return "shop";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postCreate(ModelMap modelMap, @ModelAttribute("item") ItemBL item, @CookieValue(value = "sessionID", defaultValue = "0") String session){
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
