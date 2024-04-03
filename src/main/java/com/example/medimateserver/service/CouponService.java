package com.example.medimateserver.service;

import com.example.medimateserver.entity.Coupon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CouponService {

    List<Coupon> findAll();

    Coupon findById(Integer id);

    Coupon save(Coupon Coupon);

    Coupon update(Integer id, Coupon coupon);

    void deleteById(Integer id);

    Coupon findByCode(String code);
}
