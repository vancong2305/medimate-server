package com.example.medimateserver.dto;

import jakarta.persistence.Column;

import java.math.BigInteger;

public class UserDto {
    private BigInteger id;
    private String password;
    private String email;
    private BigInteger idRole;
    private BigInteger point;
    private Integer status;
}
