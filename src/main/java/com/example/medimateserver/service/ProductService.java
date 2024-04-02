package com.example.medimateserver.service;

import com.example.medimateserver.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {

    List<Product> findAll();

    Product findById(Integer id);

    Product save(Product product);

    Product update(Integer id, Product product);

    void deleteById(Integer id);
}
