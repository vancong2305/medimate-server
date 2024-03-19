package com.example.medimateserver.repository;

import com.example.medimateserver.model.Coupon;

public interface CouponRepository {
    Coupon findByCode(String code);
}
