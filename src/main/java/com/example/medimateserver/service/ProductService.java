package com.example.medimateserver.service;

import com.example.medimateserver.dto.ProductDto;
import com.example.medimateserver.filter.Pagination;
import com.example.medimateserver.filter.ProductFilter;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {

    List<ProductDto> findAll();
    List<ProductDto> findAllWithPage(Integer id);
    List<ProductDto> findWithFilter(ProductFilter productFilter);
    List<ProductDto> findWithFilterTraditional(ProductFilter productFilter, Integer id);
    ProductDto findById(Integer id);

    ProductDto save(ProductDto productDto);

    ProductDto update(Integer id, ProductDto productDto);

    void deleteById(Integer id);
}
