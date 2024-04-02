package com.example.medimateserver.service.impl;

import com.example.medimateserver.model.Product;
import com.example.medimateserver.repository.ProductRepository;
import com.example.medimateserver.service.ProductService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() { return productRepository.findAll(); }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Integer id, Product product) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }


}
