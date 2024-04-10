package com.example.medimateserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class PaymentDto {
    private Integer idUser;
    private List<OrderDetailDto> orderDetailDtoList;
    // client chỉ cần 3 thuộc tính bên dưới, thuộc tính bên trên để xử lý logic bên server
    private CouponDetailDto couponDetail;
    private String note;
    private Integer paymentMethod;
}
