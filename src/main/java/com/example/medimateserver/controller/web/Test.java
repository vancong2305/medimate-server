package com.example.medimateserver.controller.web;

import com.example.medimateserver.model.User;
import com.example.medimateserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Test {
    @Autowired
    UserService userService;
    @GetMapping("/test")
    public String test() {
        System.out.println("Dm est cả tối");
        List<User> listUsers = userService.findAll();
        String hello = "List length is " + listUsers.size();
        return hello;
    }
}
