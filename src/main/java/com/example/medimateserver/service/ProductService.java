package com.example.medimateserver.service;

import com.example.medimateserver.dto.ProductDto;
import com.example.medimateserver.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {

    List<ProductDto> findAll();

    ProductDto findById(Integer id);

    ProductDto save(ProductDto productDto);

    ProductDto update(Integer id, ProductDto productDto);

    void deleteById(Integer id);
}
