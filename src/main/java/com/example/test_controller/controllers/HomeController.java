package com.example.test_controller.controllers;

import com.example.test_controller.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public @ResponseBody String Hello(){
        return "Hello World";
    }

    @GetMapping("/user")
    public @ResponseBody User getUser() {
        return new User("Giuseppe", "Riccobono");
    }
}
