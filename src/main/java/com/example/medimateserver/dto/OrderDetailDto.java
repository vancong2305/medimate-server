package com.example.medimateserver.dto;

import jakarta.persistence.Column;

import java.math.BigInteger;

public class OrderDetailDto {
    private BigInteger idOrder;
    private BigInteger idProduct;
    private Integer quantity;
}
