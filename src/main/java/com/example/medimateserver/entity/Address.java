package com.example.medimateserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="id_user")
    private String idUser;
    @Column(name="phone")
    private String phone;
    @Column(name="ward")
    private String ward;
    @Column(name="district")
    private String district;
    @Column(name="province")
    private String province;
    @Column(name="type")
    private String type;
    @Column(name="is_default")
    private boolean isDefault;
    @Column(name="specific_address")
    private String specific_address;
    @Column(name="status")
    private Integer status;
}
