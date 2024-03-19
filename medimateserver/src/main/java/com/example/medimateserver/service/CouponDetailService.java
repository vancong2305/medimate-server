package com.example.medimateserver.service;

import com.example.medimateserver.model.CouponDetail;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public interface CouponDetailService {

    List<CouponDetail> findAll();

    CouponDetail findById(BigInteger id);
    CouponDetail findByEmail(String email);

    CouponDetail save(CouponDetail couponDetail);

    CouponDetail update(BigInteger id, CouponDetail couponDetail);

    void deleteById(BigInteger id);
}
