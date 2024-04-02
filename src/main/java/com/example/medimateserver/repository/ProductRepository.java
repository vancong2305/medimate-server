package com.example.medimateserver.repository;

import com.example.medimateserver.model.Product;
import com.example.medimateserver.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
