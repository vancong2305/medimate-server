package com.example.medimateserver.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class CouponDetailDto {
    private Integer id;
    private Integer idUser;
    private Integer idCoupon;
    private Integer idOrder;
    private Date timeStart;
    private Date timeEnd;
    private Integer status;
}
