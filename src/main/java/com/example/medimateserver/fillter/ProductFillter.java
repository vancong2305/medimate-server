package com.example.medimateserver.fillter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductFillter {
    private Integer alphabet;
    private Integer cost;
    private Integer minCost;
    private Integer maxCost;

}
