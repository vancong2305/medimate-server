package com.example.medimateserver.controller.api;

import com.example.medimateserver.model.CouponDetail;
import com.example.medimateserver.service.CouponDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promo-rules")
public class CouponDetailController {

    @Autowired
    private CouponDetailService couponDetailService;

    @GetMapping
    public List<CouponDetail> getAllCouponDetails() {
        return couponDetailService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponDetail> getCouponDetailById(@PathVariable Integer id) {
        CouponDetail CouponDetail = couponDetailService.findById(id);
        if (CouponDetail != null) {
            return ResponseEntity.ok(CouponDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CouponDetail> createCouponDetail(@RequestBody CouponDetail CouponDetail) {
        CouponDetail savedCouponDetail = couponDetailService.save(CouponDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCouponDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCouponDetail(@PathVariable Long id, @RequestBody CouponDetail CouponDetail) {
        // ... (Implement update logic with CouponDetailService)
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCouponDetail(@PathVariable Long id) {
        // ... (Implement delete logic with CouponDetailService)
        return ResponseEntity.noContent().build();
    }
}
