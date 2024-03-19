package com.example.medimateserver.service;

import com.example.medimateserver.model.Coupon;
import com.example.medimateserver.model.Coupon;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public interface CouponService {

    List<Coupon> findAll();

    Coupon findById(BigInteger id);

    Coupon save(Coupon Coupon);

    Coupon update(BigInteger id, Coupon coupon);

    void deleteById(BigInteger id);

    Coupon findByCode(String code);
}
