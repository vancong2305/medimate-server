package com.example.medimateserver.controller.api;

import com.example.medimateserver.dto.ProductDto;
import com.example.medimateserver.filter.ProductFilter;
import com.example.medimateserver.service.ProductService;
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
@RequestMapping(value = "/api/product", produces = "application/json")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    // Get category by ID
    @GetMapping
    public ResponseEntity<?> getCategoryById() throws JsonProcessingException {
        try {
            List<ProductDto> productList = productService.findAll();
            String jsons = GsonUtil.gI().toJson(productList);
            return ResponseUtil.success(jsons);
        } catch (Exception ex) {
            return ResponseUtil.failed();
        }
    }

    @PostMapping("/filter")
    public ResponseEntity<?> getProductFilter(@RequestBody ProductFilter productFilter) throws JsonProcessingException {
        try {
            List<ProductDto> productList = productService.findWithFilter(productFilter);
            String jsons = GsonUtil.gI().toJson(productList);
            return ResponseUtil.success(jsons);
        } catch (Exception ex) {
            return ResponseUtil.failed();
        }
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<ProductDto> createCategory(@RequestBody ProductDto productDto) {
        ProductDto savedCategory = productService.save(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // Update a category
    @PutMapping
    public ResponseEntity<String> updateCategory(@PathVariable Integer id, @RequestBody ProductDto category) {
        return ResponseEntity.ok("");
    }

    // Delete a category
    @DeleteMapping
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        try {
            ProductDto product = productService.findById(id);
            product.setStatus(0);
            productService.save(product);
            return new ResponseEntity<>(
                    "Success",
                    HttpStatus.OK
            );
        } catch (Exception ex) {

        }
        return ResponseEntity.noContent().build();
    }
}
