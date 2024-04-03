package com.example.medimateserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "promo_rule")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class CouponDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "id_coupon")
    private Integer idPromoRule;
    @Column(name = "time_start")
    private Date timeStart;
    @Column(name = "time_end")
    private Date timeEnd;
    @Column(name = "status")
    private Integer status;
}
