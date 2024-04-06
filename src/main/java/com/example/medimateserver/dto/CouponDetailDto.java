package com.example.medimateserver.dto;

import com.example.medimateserver.entity.Order;
import com.example.medimateserver.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    private Date startTime;
    private Date endTime;
    private Integer status;
    private CouponDto coupon;
    private OrderDto order;
    private User user;
}
