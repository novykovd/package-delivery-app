package com.example.delivery_router_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }

}