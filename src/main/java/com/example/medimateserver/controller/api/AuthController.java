package com.example.medimateserver.controller.api;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.dto.ResponseDto;
import com.example.medimateserver.dto.TokenDto;
import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.entity.Token;
import com.example.medimateserver.entity.User;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.service.UserService;
import com.example.medimateserver.util.GsonUtil;
import com.example.medimateserver.util.MLogger;
import com.example.medimateserver.util.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth", produces = "application/json")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) throws JsonProcessingException {
        try {
            UserDto user = userService.findByPhone(userDto.getPhone());
            if (userDto.getPassword().toString().compareTo(user.getPassword().toString()) == 0) {
                String token = JwtProvider.generateToken(GsonUtil.gI().toJson(user));
                String refreshToken = JwtProvider.generateRefreshToken(GsonUtil.gI().toJson(user));
                TokenDto tokenDto = new TokenDto();
                tokenDto.setAccessToken(token);
                tokenDto.setRefreshToken(refreshToken);
                String jsons = GsonUtil.gI().toJson(tokenDto);
                tokenDto.setIdUser(user.getId());
                tokenService.save(tokenDto);
                return new ResponseEntity<>(
                        jsons,
                        HttpStatus.OK
                );
            }
             return ResponseUtil.failed();
        } catch (Exception ex) {
            return ResponseUtil.failed();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userPL) {
        try {
            userPL.setIdRole(1);
            userPL.setStatus(1);
            userPL.setRank("Đồng");
            userPL.setPoint(0);
            userService.save(userPL);
            return ResponseUtil.success();

        } catch (Exception ex) {
            return ResponseUtil.failed();
        }
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        try {
            String tokenInformation = request.getHeader("Authorization").substring(7);
            UserDto user = GsonUtil.gI().fromJson(JwtProvider.getUsernameFromToken(tokenInformation), UserDto.class);
            TokenDto tokenDto = tokenService.findById(user.getId());
            if (JwtProvider.verifyToken(tokenInformation, tokenDto)) {
                UserDto userDto1 = userService.findByPhone(user.getPhone());
                tokenService.deleteById(userDto1.getId());
                return ResponseUtil.success();
            }
             return ResponseUtil.failed();
        } catch (Exception ex) {
             return ResponseUtil.failed();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody TokenDto tokenDto, HttpServletRequest request) {
        try {
            String tokenInformation = tokenDto.getRefreshToken();
            UserDto user = GsonUtil.gI().fromJson(JwtProvider.getUsernameFromToken(tokenInformation), UserDto.class);
            TokenDto tokenDtoDatabase = tokenService.findById(user.getId());
            if (JwtProvider.verifyRefreshToken(tokenInformation, tokenDtoDatabase)) {
                // Xác thực refreshToken thành công thì xoá token cũ trong db và trả về token mới
                tokenService.deleteById(user.getId());
                String token = JwtProvider.generateToken(GsonUtil.gI().toJson(user));
                String refreshToken = JwtProvider.generateRefreshToken(GsonUtil.gI().toJson(user));
                TokenDto returnTokenDto = new TokenDto();
                returnTokenDto.setAccessToken(token);
                returnTokenDto.setRefreshToken(refreshToken);
                String jsons = GsonUtil.gI().toJson(returnTokenDto);
                returnTokenDto.setIdUser(user.getId());
                tokenService.save(returnTokenDto);
                return new ResponseEntity<>(
                        jsons,
                        HttpStatus.OK
                );
            }
             return ResponseUtil.failed();
        } catch (Exception ex) {
             return ResponseUtil.failed();
        }
    }
}
