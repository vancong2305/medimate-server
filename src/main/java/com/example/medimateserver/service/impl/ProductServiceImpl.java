package com.example.medimateserver.service.impl;

import com.example.medimateserver.dto.ProductDto;
import com.example.medimateserver.entity.Product;
import com.example.medimateserver.filter.ProductFilter;
import com.example.medimateserver.repository.ProductRepository;
import com.example.medimateserver.service.ProductService;
import com.example.medimateserver.util.ConvertUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        System.out.println(products.get(0).getUnit().getName());
        return products.stream()
                .map(product -> ConvertUtil.gI().toDto(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findWithFilter(ProductFilter productFilter) {
        List<Product> products = productRepository.findWithFilter(productFilter.getMinPrice(), productFilter.getMaxPrice(), productFilter.getKeySearch());
        return products.stream()
                .map(product -> ConvertUtil.gI().toDto(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Integer id) {
        return productRepository.findById(id)
                .map(product -> ConvertUtil.gI().toDto(product, ProductDto.class))
                .orElse(null);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = ConvertUtil.gI().toEntity(productDto, Product.class);
        product = productRepository.save(product);
        return ConvertUtil.gI().toDto(product, ProductDto.class);
    }

    @Override
    public ProductDto update(Integer id, ProductDto product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));

        // Cập nhật các thuộc tính từ categoryDto sang existingCategory
        existingProduct.setName(product.getName()); // Ví dụ, gán name mới
        // Cập nhật các thuộc tính khác nếu cần

        Product updatedCategory = productRepository.save(existingProduct);
        return ConvertUtil.gI().toDto(updatedCategory, ProductDto.class);
    }

    @Override
    public void deleteById(Integer id) {

    }


}
