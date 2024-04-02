package com.example.medimateserver.dto;

import jakarta.persistence.Column;

public class OrderDto {
    private Integer id;
    private Integer idUser;
    private Integer idPromo;
    private Integer paymentMethod;
    private Integer status;
}
