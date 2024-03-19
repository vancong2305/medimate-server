package com.example.medimateserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "promo_rule")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class CouponDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(name = "id_user")
    private BigInteger idUser;
    @Column(name = "id_coupon")
    private BigInteger idPromoRule;
    @Column(name = "code")
    private String code;
    @Column(name = "time_start")
    private Date timeStart;
    @Column(name = "time_end")
    private Date timeEnd;
    @Column(name = "status")
    private Integer status;
}
