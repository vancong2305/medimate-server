package com.example.medimateserver.controller.api;

import com.example.medimateserver.dto.CouponDto;
import com.example.medimateserver.entity.Coupon;
import com.example.medimateserver.service.CouponService;
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

    @GetMapping
    public List<CouponDto> getAllCoupons() {
        return couponService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponDto> getCouponByCode(@PathVariable String code) {
        CouponDto couponDto = couponService.findByCode(code);
        if (couponDto != null) {
            return ResponseEntity.ok(couponDto);
        } else {
            return ResponseEntity.notFound().build();
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
