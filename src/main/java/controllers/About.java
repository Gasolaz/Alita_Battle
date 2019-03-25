package controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/about")
@Controller
public class About {
    @GetMapping
    public String getAbout(){
        return "about";
    }
}
