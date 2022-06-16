package com.leord.car_rental;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @GetMapping("/")
    public String landingPage() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }

    @GetMapping("/user/index")
    public String userHome(){
        return "user/index";
    }

    @GetMapping("/admin/index")
    public String adminHome() {
        return "admin/index";
    }
}
