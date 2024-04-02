package com.example.medimateserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "id_promo")
    private Integer idPromo;
    @Column(name = "payment_method")
    private Integer paymentMethod;
    @Column(name = "status")
    private Integer status;
}
