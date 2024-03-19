package com.example.medimateserver.service;

import com.example.medimateserver.model.Category;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public interface CategoryService {

    List<Category> findAll();

    Category findById(BigInteger id);
    Category findByEmail(String email);

    Category save(Category category);

    Category update(BigInteger id, Category category);

    void deleteById(BigInteger id);
}
