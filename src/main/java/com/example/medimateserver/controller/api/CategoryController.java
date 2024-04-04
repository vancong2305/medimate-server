package com.example.medimateserver.controller.api;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.dto.CategoryDto;
import com.example.medimateserver.dto.TokenDto;
import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.entity.User;
import com.example.medimateserver.fillter.Pagination;
import com.example.medimateserver.service.CategoryService;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.service.UserService;
import com.example.medimateserver.util.CheckAuthUtil;
import com.example.medimateserver.util.GsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
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
    private CategoryService categoryService;

    @Autowired
    private UserService userService;



    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getCategoryById(@PathVariable Integer id, HttpServletRequest request) throws JsonProcessingException {
        String pageInformation = request.getHeader("Pagination");
        String tokenInformation = request.getHeader("Token");
//        System.out.println("Lấy thông tin phân trang " + pageInformation);
//
//        //Lấy thông tin người dùng qua id và so sánh với token
//        User user = userService.findById(id);
//        System.out.println("User from token: " + JwtProvider.getUsernameFromToken(tokenInformation));
//
//        // Chuyển JSON sang object
//        Pagination studentFromJson = GsonUtil.getInstance().fromJson(pageInformation, Pagination.class);
//        String json = GsonUtil.getInstance().toJson(studentFromJson);
//
//        String filterValue = studentFromJson.getFillter();
//        if (filterValue == null) {
//            System.out.println("null");
//        } else {
//            System.out.println("1");
//        }
//        List<User> userList = userService.findAll();
//        String jsonUser = GsonUtil.getInstance().toJson(userList);
        TokenDto tokenDto = tokenService.findById(id);

        if (CheckAuthUtil.gI().check(tokenInformation,tokenDto.getAccessToken(), id)) {
            List<CategoryDto> categoryList = categoryService.findAll();
            String jsons = GsonUtil.gI().toJson(categoryList);
            return new ResponseEntity<>(
                    jsons,
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                "badRequest",
                HttpStatus.BAD_REQUEST
        );
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.save(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // Update a category
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer id, @RequestBody CategoryDto category) {
        return ResponseEntity.ok("");
    }

    // Delete a category
    @DeleteMapping("/{id}")
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
