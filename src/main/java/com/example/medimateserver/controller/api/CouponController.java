package com.example.medimateserver.controller.api;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.dto.CouponDto;
import com.example.medimateserver.dto.TokenDto;
import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.entity.Coupon;
import com.example.medimateserver.service.CouponService;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.util.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<?> getAllCoupons(HttpServletRequest request) {
        try {
            String tokenInformation = request.getHeader("Authorization");
            tokenInformation = tokenInformation.substring(7);
            UserDto user = GsonUtil.gI().fromJson(JwtProvider.getUsernameFromToken(tokenInformation), UserDto.class);
            TokenDto tokenDto = tokenService.findById(user.getId());
            if (JwtProvider.verifyToken(tokenInformation, tokenDto)) {
                return new ResponseEntity<>(couponService.findAll(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST.getReasonPhrase() + " Wrong token!", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>("Errorr: " + ex.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<CouponDto> createCoupon(@RequestBody CouponDto couponDto) {
        CouponDto savedCoupon = couponService.save(couponDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoupon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCoupon(@PathVariable String code, @RequestBody Coupon Coupon) {
        return ResponseEntity.ok("updated Coupon");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable String code) {
        // ... (Implement delete logic with CouponService)
        return ResponseEntity.noContent().build();
    }
}
