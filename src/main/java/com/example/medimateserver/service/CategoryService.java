package com.example.medimateserver.service;

import com.example.medimateserver.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {

    List<Category> findAll();

    Category findById(Integer id);

    Category save(Category category);

    Category update(Integer id, Category category);

    void deleteById(Integer id);
}
