package com.example.medimateserver.dto;

import jakarta.persistence.Column;

import java.math.BigInteger;
import java.util.Date;

public class CouponDto {

    private BigInteger id;
    private BigInteger necessaryPoints;
    private Integer percentDecrease;
    private Integer status;
}
