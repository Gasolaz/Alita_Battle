package controllers;


import models.CustomCharacter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequestMapping("/waiting")
@Controller
public class Refresh {

    @GetMapping
    public String postArena(@ModelAttribute CustomCharacter customCharacter, Map<String, Object> model, @CookieValue(value = "sessionID", defaultValue = "0") String session) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:/arena";
    }
}
