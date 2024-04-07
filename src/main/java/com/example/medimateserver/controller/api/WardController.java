package com.example.medimateserver.controller.api;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.dto.DistrictDto;
import com.example.medimateserver.dto.ProvinceDto;
import com.example.medimateserver.dto.TokenDto;
import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.service.ProvinceService;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.service.UserService;
import com.example.medimateserver.service.WardService;
import com.example.medimateserver.util.GsonUtil;
import com.example.medimateserver.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/ward", produces = "application/json")
public class WardController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private WardService wardService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getWardByDistrict(HttpServletRequest request, @PathVariable Integer id) {
        try {
            String tokenInformation = request.getHeader("Authorization");
            tokenInformation = tokenInformation.substring(7);
            UserDto user = GsonUtil.gI().fromJson(JwtProvider.getUsernameFromToken(tokenInformation), UserDto.class);
            TokenDto tokenDto = tokenService.findById(user.getId());
            if (JwtProvider.verifyToken(tokenInformation, tokenDto)) {
                String json = GsonUtil.gI().toJson(wardService.findByIdDistrict(id));
                return ResponseUtil.success(json);
            }
            return ResponseUtil.failed();
        } catch (Exception ex) {
            return ResponseUtil.failed();
        }
    }

}
