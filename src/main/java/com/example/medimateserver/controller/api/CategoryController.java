package com.example.medimateserver.controller.api;

import com.example.medimateserver.dto.CategoryDto;
import com.example.medimateserver.service.CategoryService;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.service.UserService;
import com.example.medimateserver.util.GsonUtil;
import com.example.medimateserver.util.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category", produces = "application/json")
public class CategoryController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    // Get category by ID
    @GetMapping
    public ResponseEntity<?> getCategoryById() throws JsonProcessingException {
        try {
            List<CategoryDto> categoryList = categoryService.findAll();
            String jsons = GsonUtil.gI().toJson(categoryList);
            return ResponseUtil.success(jsons);
        } catch (Exception ex) {
            return ResponseUtil.failed();
        }

    }

    // Create a new category
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.save(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // Update a category
    @PutMapping
    public ResponseEntity<String> updateCategory(@PathVariable Integer id, @RequestBody CategoryDto category) {
        return ResponseEntity.ok("");
    }

    // Delete a category
    @DeleteMapping
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        try {
            CategoryDto category = categoryService.findById(id);
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
