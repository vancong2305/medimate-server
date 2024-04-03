package com.example.medimateserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unit")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private Integer name;
    @Column(name = "status")
    private Integer status;

}
