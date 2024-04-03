package com.example.medimateserver.service.impl;

import com.example.medimateserver.entity.CouponDetail;
import com.example.medimateserver.repository.CouponDetailRepository; // Giả sử bạn đã tạo Repository này
import com.example.medimateserver.service.CouponDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponDetailServiceImpl implements CouponDetailService {

    @Autowired
    private CouponDetailRepository couponDetailRepository;

    @Override
    public List<CouponDetail> findAll() {
        return couponDetailRepository.findAll();
    }

    @Override
    public CouponDetail findById(Integer id) {
        Optional<CouponDetail> couponDetailOptional = couponDetailRepository.findById(id);
        return couponDetailOptional.orElse(null); // Hoặc ném Exception nếu thích hợp
    }


    @Override
    public CouponDetail save(CouponDetail couponDetail) {
        return couponDetailRepository.save(couponDetail);
    }

    @Override
    public CouponDetail update(Integer id, CouponDetail couponDetail) {
        Optional<CouponDetail> existingCouponDetailOptional = couponDetailRepository.findById(id);
        if (existingCouponDetailOptional.isPresent()) {
            CouponDetail existingCouponDetail = existingCouponDetailOptional.get();
            // Cập nhật các trường của existingCouponDetail bằng dữ liệu từ couponDetail
            // ...

            return couponDetailRepository.save(existingCouponDetail);
        } else {
            return null; // Hoặc ném Exception nếu thích hợp
        }
    }

    @Override
    public void deleteById(Integer id) {
        couponDetailRepository.deleteById(id);
    }
}
