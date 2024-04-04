package com.example.medimateserver.repository;

import com.example.medimateserver.entity.Category;
import com.example.medimateserver.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
}
