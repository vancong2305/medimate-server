package com.example.medimateserver.dto;

import jakarta.persistence.Column;

import java.math.BigInteger;

public class OrderDto {
    private BigInteger id;
    private BigInteger idUser;
    private BigInteger idPromo;
    private Integer paymentMethod;
    private Integer status;
}
