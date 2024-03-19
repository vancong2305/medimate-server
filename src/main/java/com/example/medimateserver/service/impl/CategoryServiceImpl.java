package com.example.medimateserver.service.impl;

import com.example.medimateserver.model.Category;
import com.example.medimateserver.service.CategoryService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category findById(BigInteger id) {
        return null;
    }

    @Override
    public Category findByEmail(String email) {
        return null;
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Category update(BigInteger id, Category category) {
        return null;
    }

    @Override
    public void deleteById(BigInteger id) {

    }
}
