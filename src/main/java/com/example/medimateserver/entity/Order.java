package com.example.medimateserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "code")
    private Integer code;
    @Column(name = "payment_method")
    private Integer paymentMethod;
    @Column(name = "discount_coupon")
    private Integer discountCoupon;
    @Column(name = "delivery_fee")
    private Integer deliveryFee;
    @Column(name = "order_time")
    private Integer orderTime;
    @Column(name = "note")
    private Integer note;
    @Column(name = "point")
    private Integer point;
    @Column(name = "status")
    private Integer status;
}
