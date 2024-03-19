package com.example.medimateserver.service;

import com.example.medimateserver.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public interface ProductService {

    List<Product> findAll();

    Product findById(BigInteger id);
    Product findByEmail(String email);

    Product save(Product product);

    Product update(BigInteger id, Product product);

    void deleteById(BigInteger id);
}
