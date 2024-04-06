package com.example.medimateserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "coupon_detail")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class CouponDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_coupon")
    private Integer idCoupon;

    @Column(name = "id_user") // <--- Điểm quan trọng
    private Integer idUser;

    @Column(name = "id_oder")
    private Integer idOrder;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "status")
    private Integer status;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne
    @JoinColumn(name = "id_coupon")
    private Coupon coupon;

    @OneToOne
    @JoinColumn(name = "id_oder")
    private Order order;
}
