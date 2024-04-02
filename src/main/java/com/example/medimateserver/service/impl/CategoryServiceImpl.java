package com.example.medimateserver.service.impl;

import com.example.medimateserver.model.Category;
import com.example.medimateserver.repository.CategoryRepository;
import com.example.medimateserver.service.CategoryService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    Gson gson = new Gson();
    CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return null;
    }



    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Integer id, Category category) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }


}
