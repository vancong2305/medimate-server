package com.example.medimateserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "order_detail")
@Data // Bao gồm getter, setter, toString, equals, hashCode
@NoArgsConstructor  // Constructor không tham số
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;
    @Column(name = "discount_price")
    private Integer discountPrice;
    @Column(name = "quantity")
    private Integer quantity;
    // Constructor với tham số
    public OrderDetail(OrderDetailId id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }
    // Lớp khóa chính kép với Lombok
    @Embeddable
    @Data
    @NoArgsConstructor
    public static class OrderDetailId implements Serializable {
        @Column(name = "id_order")
        private Integer idOrder;
        @Column(name = "id_product")
        private Integer idProduct;
    }
}
