package com.example.medimateserver.repository;

import com.example.medimateserver.model.CouponDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponDetailRepository extends JpaRepository<CouponDetail, Integer> {

}
