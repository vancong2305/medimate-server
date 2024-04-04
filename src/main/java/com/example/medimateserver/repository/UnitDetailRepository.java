package com.example.medimateserver.repository;

import com.example.medimateserver.entity.UnitDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitDetailRepository extends JpaRepository<UnitDetail, Integer> {
}
