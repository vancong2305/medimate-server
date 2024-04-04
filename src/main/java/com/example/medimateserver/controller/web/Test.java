package com.example.medimateserver.controller.web;

import com.example.medimateserver.entity.User;
import com.example.medimateserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Test {
    @Autowired
    UserService userService;
    @GetMapping("/test")
    public String test() {
        System.out.println("Test cả tối");
        return "Test cả tối";
    }
}
