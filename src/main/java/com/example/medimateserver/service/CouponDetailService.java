package com.example.medimateserver.service;

import com.example.medimateserver.entity.CouponDetail;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CouponDetailService {

    List<CouponDetail> findAll();

    CouponDetail findById(Integer id);

    CouponDetail save(CouponDetail couponDetail);

    CouponDetail update(Integer id, CouponDetail couponDetail);

    void deleteById(Integer id);
}
