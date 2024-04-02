package com.example.medimateserver.fillter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pagination {
    private Integer all;
    private Integer pageSize;
    private Integer currentPage;
    private String fillter;
}
