package com.example.medimateserver.controller.api;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.fillter.Pagination;
import com.example.medimateserver.model.Category;
import com.example.medimateserver.model.Gcustome;
import com.example.medimateserver.model.User;
import com.example.medimateserver.service.CategoryService;
import com.example.medimateserver.service.UserService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserService userService;

    // Get all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getCategoryById(@PathVariable Integer id, HttpServletRequest request) {
//        String pageInformation = request.getHeader("Pagination");
//        String tokenInformation = request.getHeader("Token");
//        System.out.println("Lấy thông tin phân trang " + pageInformation);
//
//        //Lấy thông tin người dùng qua id và so sánh với token
//        User user = userService.findById(id);
//
//        System.out.println("User from token: " + JwtProvider.getUsernameFromToken(tokenInformation));
//
//        // Chuyển JSON sang object
//        Pagination studentFromJson = Gcustome.getInstance().fromJson(pageInformation, Pagination.class);
//        String json = Gcustome.getInstance().toJson(studentFromJson);
//
//        String filterValue = studentFromJson.getFillter();
//        if (filterValue == null) {
//            System.out.println("null");
//        } else {
//            System.out.println("1");
//        }
//        List<User> userList = userService.findAll();
//        String jsonUser = Gcustome.getInstance().toJson(userList);

        List<Category> categoryList = categoryService.findAll();
        String jsons = Gcustome.getInstance().toJson(categoryList);

        return new ResponseEntity<>(
                jsons,
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
