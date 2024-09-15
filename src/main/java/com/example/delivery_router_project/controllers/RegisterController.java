package com.example.delivery_router_project.controllers;

import com.example.delivery_router_project.entities.AccountEntity;
import com.example.delivery_router_project.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    AccountRepository repo;

    @GetMapping
    public String registration(Model model){
        model.addAttribute("user", new AccountEntity());
        return "register";
    }

    @PostMapping
    public void submitRegister(@ModelAttribute("user") AccountEntity user){
        repo.save(user);
    }
}
