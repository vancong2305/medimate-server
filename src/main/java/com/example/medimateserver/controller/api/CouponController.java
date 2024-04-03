package com.example.medimateserver.controller.api;

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
    private CouponService CouponService;

    @GetMapping
    public List<Coupon> getAllCoupons() {
        return CouponService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coupon> getCouponByCode(@PathVariable String code) {
        Coupon Coupon = CouponService.findByCode(code);
        if (Coupon != null) {
            return ResponseEntity.ok(Coupon);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon Coupon) {
        Coupon savedCoupon = CouponService.save(Coupon);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoupon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCoupon(@PathVariable String code, @RequestBody Coupon Coupon) {
        return ResponseEntity.ok("updatedCoupon");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable String code) {
        // ... (Implement delete logic with CouponService)
        return ResponseEntity.noContent().build();
    }
}
