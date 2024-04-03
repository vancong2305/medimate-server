package com.example.medimateserver.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Integer id;
    private String name;
    private Integer status;
    private List<ProductDto> productDtoList;
}

