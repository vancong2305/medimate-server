package com.example.medimateserver.controller.api;

import com.example.medimateserver.model.Category;
import com.example.medimateserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/category")
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
    public ResponseEntity<Category> getCategoryById(@PathVariable BigInteger id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // Update a category
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable BigInteger id, @RequestBody Category category) {
        // ... (Implement update logic with CategoryService)
        return ResponseEntity.ok("");
    }

    // Delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable BigInteger id) {
        // ... (Implement delete logic with CategoryService)
        return ResponseEntity.noContent().build();
    }
}
