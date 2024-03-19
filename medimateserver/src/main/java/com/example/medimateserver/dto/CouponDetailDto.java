package com.example.medimateserver.dto;

import jakarta.persistence.Column;

import java.math.BigInteger;
import java.util.Date;

public class CouponDetailDto {
    private BigInteger id;
    private BigInteger idUser;
    private BigInteger idPromoRule;
    private String code;
    private Date timeStart;
    private Date timeEnd;
    private Integer status;
}
