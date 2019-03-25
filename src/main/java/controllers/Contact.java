package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/contact")
@Controller
public class Contact {
    @GetMapping
    public String getContact(){
        return "contact";
    }
}
