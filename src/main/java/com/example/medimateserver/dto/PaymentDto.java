package com.example.medimateserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class PaymentDto {
    private Integer idUser;
    private List<OrderDetailDto> orderDetailDtoList;
    private CouponDetailDto couponDetail;
    private String note;
    private Integer paymentMethod;
}
