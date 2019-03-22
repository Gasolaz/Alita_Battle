package controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

import static DB.TableCreation.createTables;

@RequestMapping("")
@Controller
public class HelloController {

    @GetMapping
    public String printHello(ModelMap model) {
        try {
            createTables();
        } catch(IOException e){
            e.printStackTrace();
        }

//        model.addAttribute("AccessDenied", accessDenied);
        return "index";

    }
}
