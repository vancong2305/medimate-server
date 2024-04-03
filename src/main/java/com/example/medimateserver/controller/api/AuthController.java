package com.example.medimateserver.controller.api;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.entity.User;
import com.example.medimateserver.service.UserService;
import com.example.medimateserver.util.GsonUtil;
import com.example.medimateserver.util.MLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService userService;

    /*
        First, users provide the server with their information. Could be email, password...
        Second, we compare emails and passwords from users and databases.
        Finally, based on the condition return the token or return an invalid request to the user.
    */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        try {
            UserDto user = userService.findByEmail(userDto.getEmail());
            if (userDto.getPassword().toString().compareTo(user.getPassword().toString()) == 0) {
                String token = JwtProvider.generateToken(GsonUtil.gI().toJson(user));
                System.out.println("Email has been received: " + userDto.getEmail());
                System.out.println("Token after generated: " + token);
                System.out.println("User from token: " + JwtProvider.getUsernameFromToken(token));

                Map<String, Object> responseMap = new HashMap<>();
                responseMap.putAll(Collections.singletonMap("token", token));


                return new ResponseEntity<>(
                        responseMap,
                        HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                    "Invalid data. Password Error!",
                    HttpStatus.BAD_REQUEST
            );
        } catch (Exception ex) {
            return new ResponseEntity<>(
                    "Invalid data. Email Error!",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    /*
        We set default role for new beginer is one
        The point of them is zero
        We set email is the only field by command <ALTER TABLE `user` ADD UNIQUE(`email`)>
    */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userPL) {
        try {
            userPL.setIdRole(1);
            userPL.setStatus(1);
            userPL.setPoint(0);
            userService.save(userPL);
            return new ResponseEntity<>(
                    "Register Success!",
                    HttpStatus.OK
            );
        } catch (Exception ex) {
            MLogger.LOGGER.severe("Error: " + ex.getMessage());
            return new ResponseEntity<>(
                    "Error Or User Already Exists!" + ex.toString(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
