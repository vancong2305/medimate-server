package com.example.medimateserver.service;

import com.example.medimateserver.model.CouponDetail;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CouponDetailService {

    List<CouponDetail> findAll();

    CouponDetail findById(Integer id);
    CouponDetail findByEmail(String email);

    CouponDetail save(CouponDetail couponDetail);

    CouponDetail update(Integer id, CouponDetail couponDetail);

    void deleteById(Integer id);
}
