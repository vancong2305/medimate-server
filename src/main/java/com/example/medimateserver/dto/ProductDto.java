package com.example.medimateserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private Integer idCategory;
    private String name;
    private String description;
    private Integer percent;
    private Integer status;
}
