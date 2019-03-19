package firstPackage;

import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/login")
@Controller
public class Login {

    @GetMapping
    public String getLogin() {
        return "redirect:/";
    }

    @PostMapping
    public String postLogin(Map<String, Object> model, @ModelAttribute User user){
        String greeting = "Hello " + user.getName();
        model.put("greeting", greeting);
        return "login";
    }
}
