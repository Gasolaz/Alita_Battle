package com.test.pluto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/register")
@Controller
public class Registration {

    @GetMapping
    public String getLogin() {
        return "redirect:/";
    }

    @PostMapping
    public String postLogin(){
        return "login";
    }
}