package com.example.medimateserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "default_percent_decrease")
    private Integer defaultPercentDecrease;
    @Column(name = "status")
    private Integer status;
}
