package com.example.medimateserver.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class CouponDto {

    private Integer id;
    private Integer necessaryPoints;
    private Integer percentDecrease;
    private Integer status;
}
