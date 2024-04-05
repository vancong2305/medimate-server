package com.example.medimateserver.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer id;
    private Integer idUser;
    private String code;
    private Integer paymentMethod;
    private Integer discountCoupon;
    private Integer deliveryFee;
    private Date orderTime;
    private String note;
    private Integer point;
    private Integer status;
}
