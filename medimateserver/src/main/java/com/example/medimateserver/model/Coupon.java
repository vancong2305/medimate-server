package com.example.medimateserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "promo")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(name = "necessary_points")
    private BigInteger necessaryPoints;
    @Column(name = "percent_decrease")
    private Integer percentDecrease;
    @Column(name = "status")
    private Integer status;
}
