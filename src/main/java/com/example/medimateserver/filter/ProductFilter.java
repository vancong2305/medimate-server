package com.example.medimateserver.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductFilter {
    private Integer minPrice;
    private Integer maxPrice;
    private String keySearch;
}
