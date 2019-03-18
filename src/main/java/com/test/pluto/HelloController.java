package com.test.pluto;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("")
@Controller
public class HelloController {

    @GetMapping
    public String printHello(ModelMap model) {

        model.addAttribute("message", "Helljskglo Spring Tipo testinam");
        return "index";

    }
}
