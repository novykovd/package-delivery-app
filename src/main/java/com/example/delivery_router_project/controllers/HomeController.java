package com.example.delivery_router_project.controllers;

import com.example.delivery_router_project.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    DataService data;

    @GetMapping
    public String home(Model model, Principal principal){
        Authentication authentication = (Authentication) principal;
        String username = authentication.getName();

        model.addAttribute("graph", data.getGraphOfCity(username));
        model.addAttribute("user_packages", data.getMyPackages(username) );
        model.addAttribute("routes", data.getRoutes(username));

        return "home";
    }

}
