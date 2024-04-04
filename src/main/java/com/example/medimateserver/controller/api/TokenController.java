package com.example.medimateserver.controller.api;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.dto.TokenDto;
import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.entity.Token;
import com.example.medimateserver.service.ProductService;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.util.GsonUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;
    @PostMapping("/check")
    public ResponseEntity<Boolean> checkToken(@RequestBody TokenDto token) {
        try {
            UserDto user = GsonUtil.gI().fromJson(JwtProvider.getUsernameFromToken(token.getAccessToken()), UserDto.class);
            TokenDto tokenDto = tokenService.findById(user.getId());
            boolean check = JwtProvider.verifyToken(token.getAccessToken(), tokenDto);

            if (check) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
            }
        } catch (JwtException e) {
            // Xử lý lỗi token không hợp lệ
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
    }
}
