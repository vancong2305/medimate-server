package com.example.medimateserver.controller.api;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.dto.CouponDetailDto;
import com.example.medimateserver.dto.CouponDto;
import com.example.medimateserver.dto.TokenDto;
import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.entity.CouponDetail;
import com.example.medimateserver.service.CouponDetailService;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.util.GsonUtil;
import com.example.medimateserver.util.ResponseUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/coupon_detail", produces = "application/json")
public class CouponDetailController {
    @Autowired
    private CouponDetailService couponDetailService;
    @Autowired
    private TokenService tokenService;
    @GetMapping
    public ResponseEntity<?> getAllCouponDetails(HttpServletRequest request) {
        try {
            String tokenInformation = request.getHeader("Authorization");
            tokenInformation = tokenInformation.substring(7);
            UserDto user = GsonUtil.gI().fromJson(JwtProvider.getUsernameFromToken(tokenInformation), UserDto.class);
            TokenDto tokenDto = tokenService.findById(user.getId());
            if (JwtProvider.verifyToken(tokenInformation, tokenDto)) {
                return new ResponseEntity<>(couponDetailService.findByUserId(user.getId()), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST.getReasonPhrase() + " Wrong token!", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>("Errorr: " + ex.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<?> getCouponDetailById(HttpServletRequest request, @RequestBody CouponDetailDto couponDetailDto) {
        try {
            String tokenInformation = request.getHeader("Authorization");
            tokenInformation = tokenInformation.substring(7);
            UserDto user = GsonUtil.gI().fromJson(JwtProvider.getUsernameFromToken(tokenInformation), UserDto.class);
            TokenDto tokenDto = tokenService.findById(user.getId());
            if (JwtProvider.verifyToken(tokenInformation, tokenDto)) {
                couponDetailDto.setIdOrder(null);
                couponDetailService.save(couponDetailDto);
                return ResponseUtil.success();
            }
            return ResponseUtil.failed();
        } catch (Exception ex) {
            return ResponseUtil.failed();
        }
    }
    @PutMapping
    public ResponseEntity<String> updateCouponDetail(@PathVariable Long id, @RequestBody CouponDetail CouponDetail) {
        // ... (Implement update logic with CouponDetailService)
        return ResponseEntity.ok("success");
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteCouponDetail(@PathVariable Long id) {
        // ... (Implement delete logic with CouponDetailService)
        return ResponseEntity.noContent().build();
    }
}
