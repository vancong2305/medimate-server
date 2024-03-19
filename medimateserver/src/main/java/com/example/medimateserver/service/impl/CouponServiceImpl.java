package com.example.medimateserver.service.impl;

import com.example.medimateserver.model.Coupon;
import com.example.medimateserver.service.CouponService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public class CouponServiceImpl implements CouponService {
    @Override
    public List<Coupon> findAll() {
        return null;
    }

    @Override
    public Coupon findById(BigInteger id) {
        return null;
    }


    @Override
    public Coupon save(Coupon Coupon) {
        return null;
    }

    @Override
    public Coupon update(BigInteger id, Coupon coupon) {
        return null;
    }

    @Override
    public void deleteById(BigInteger id) {

    }

    @Override
    public Coupon findByCode(String code) {
        return null;
    }

}
