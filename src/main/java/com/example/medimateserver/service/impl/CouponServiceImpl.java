package com.example.medimateserver.service.impl;

import com.example.medimateserver.entity.Coupon;
import com.example.medimateserver.repository.CouponRepository;
import com.example.medimateserver.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponRepository couponRepository;

    @Override
    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon findById(Integer id) {
        return couponRepository.findById(id).orElse(null);
    }


    @Override
    public Coupon save(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon update(Integer id, Coupon coupon) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Coupon findByCode(String code) {
        return null;
    }

}
