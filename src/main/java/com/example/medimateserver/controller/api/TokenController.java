package com.example.medimateserver.controller.api;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.dto.TokenDto;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkToken(@RequestBody TokenDto token) {
        try {
            // Giải mã token và lấy thông tin
            boolean check = JwtProvider.verifyToken(token.getToken());
            System.out.println("Token is: " + token);
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
