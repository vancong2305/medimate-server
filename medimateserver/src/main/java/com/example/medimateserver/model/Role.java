package com.example.medimateserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;
@Entity
@Table(name = "role")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private Integer status;
}
