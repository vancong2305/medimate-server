package com.example.medimateserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unit_detail")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class UnitDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_unit")
    private Integer idUnit;
    @Column(name = "id_product")
    private Integer idProduct;
    @Column(name = "id_convert_unit")
    private Integer idConvertUnit;
    @Column(name = "conversion_point")
    private Integer conversionPoint;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "status")
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "id_unit", insertable = false, updatable = false)
    private Unit unit; // Add this field
}
