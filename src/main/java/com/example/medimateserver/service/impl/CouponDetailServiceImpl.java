package com.example.medimateserver.service.impl;

import com.example.medimateserver.dto.CouponDetailDto;
import com.example.medimateserver.dto.CouponDetailDto;
import com.example.medimateserver.dto.CouponDto;
import com.example.medimateserver.dto.OrderDetailDto;
import com.example.medimateserver.entity.Coupon;
import com.example.medimateserver.entity.CouponDetail;
import com.example.medimateserver.entity.CouponDetail;
import com.example.medimateserver.entity.OrderDetail;
import com.example.medimateserver.repository.CouponDetailRepository; // Giả sử bạn đã tạo Repository này
import com.example.medimateserver.repository.CouponRepository;
import com.example.medimateserver.service.CouponDetailService;
import com.example.medimateserver.service.CouponService;
import com.example.medimateserver.util.ConvertUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponDetailServiceImpl implements CouponDetailService {

    @Autowired
    private CouponDetailRepository couponDetailRepository;
    @Autowired
    private CouponService couponService;

    @Override
    public List<CouponDetail> findAll() {
        List<CouponDetail> couponDetailList = couponDetailRepository.findAll();
        return couponDetailRepository.findAll();
    }

    @Override
    public CouponDetail findById(Integer id) {
        Optional<CouponDetail> couponDetailOptional = couponDetailRepository.findById(id);
        return couponDetailOptional.orElse(null); // Hoặc ném Exception nếu thích hợp
    }

    @Override
    public List<CouponDetailDto> findByUserId(Integer id) {
        List<CouponDetail> couponDetailList = couponDetailRepository.findByIdUser(id);
        return couponDetailList.stream()
                .map(couponDetail -> ConvertUtil.gI().toDto(couponDetail, CouponDetailDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public CouponDetailDto save(CouponDetailDto couponDetailDto) {
        CouponDetail couponDetail = ConvertUtil.gI().toEntity(couponDetailDto, CouponDetail.class);
        System.out.println("ID là: " + couponDetail.getIdCoupon());
        CouponDto couponDto = couponService.findById(couponDetail.getIdCoupon());
        System.out.println("coupon la " + couponDto.getExpirationTime());
        couponDetail.setIdCoupon(couponDto.getId());
        couponDetail = couponDetailRepository.save(couponDetail);
        System.out.println("id coupon " + couponDetail.getIdCoupon());
        System.out.println("id user " + couponDetail.getIdUser());
        return ConvertUtil.gI().toDto(couponDetail, CouponDetailDto.class);
    }

    @Override
    public CouponDetailDto update(Integer id, CouponDetailDto CouponDetailDto) {
        CouponDetail existingCouponDetail = couponDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CouponDetail not found with id: " + id));


        CouponDetail updatedCouponDetail = couponDetailRepository.save(existingCouponDetail);
        return ConvertUtil.gI().toDto(updatedCouponDetail, CouponDetailDto.class);
    }

    @Override
    public void deleteById(Integer id) {
        couponDetailRepository.deleteById(id);
    }
}
