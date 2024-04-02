package com.example.medimateserver.repository;

import com.example.medimateserver.model.Category;
import com.example.medimateserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
