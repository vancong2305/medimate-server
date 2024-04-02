package com.example.medimateserver.controller.api;

import com.example.medimateserver.model.Category;
import com.example.medimateserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/category", produces = "application/json")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Get all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getCategoryById(@PathVariable Long id) {
        System.out.println("Vào đây rồi nè"); // Dùng để gỡ lỗi
        return new ResponseEntity<>(
                "Success",
                HttpStatus.OK
        );
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // Update a category
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        return ResponseEntity.ok("");
    }

    // Delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        try {
            Category category = categoryService.findById(id);
            category.setStatus(0);
            categoryService.save(category);
            return new ResponseEntity<>(
                    "Success",
                    HttpStatus.OK
            );
        } catch (Exception ex) {

        }
        return ResponseEntity.noContent().build();
    }
}
