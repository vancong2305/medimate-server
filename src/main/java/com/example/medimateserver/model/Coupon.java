package com.example.medimateserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "promo")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "code")
    private String code;
    @Column(name = "expiration_time")
    private String expirationTime;
    @Column(name = "necessary_points")
    private Integer necessaryPoints;
    @Column(name = "percent_decrease")
    private Integer percentDecrease;
    @Column(name = "status")
    private Integer status;
}
