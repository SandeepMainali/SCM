package com.scm.controllers;


import com.scm.DTO.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/about")
    public String aboutPage(){
        return "about";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @RequestMapping("/services")
    public String servicesPage() {
        return "services";
    }

    @RequestMapping("/search")
    public String searchPage() {
        return "search";
    }

    @RequestMapping("/navbar")
    public String navbarPage() {
        return "user/user_navbar";
    }

    @RequestMapping("/")
    public String homePage() {
        return "home";
    }

    @RequestMapping("/home")
    public String ShowhomePage() {
        return "home";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "contact";
    }
}
