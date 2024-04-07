package com.example.medimateserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_detail")
@Data
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // id là khóa chính duy nhất
    @Column(name = "id_order")
    private Integer idOrder;
    @Column(name = "id_product")
    private Integer idProduct;
    @Column(name = "discount_price")
    private Integer discountPrice;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private OrderEntity orderEntity;
    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    private Product product;
}
