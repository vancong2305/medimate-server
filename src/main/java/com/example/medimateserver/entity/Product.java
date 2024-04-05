package com.example.medimateserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "product")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_category")
    private Integer idCategory;
    @Column(name = "id_unit")
    private Integer idUnit;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "discount_percent")
    private Integer discountPercent;
    @Column(name = "price")
    private Integer price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "image")
    private String image;
    @Column(name = "status")
    private Integer status;
}
