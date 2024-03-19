package com.example.medimateserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "order_detail")
@Data // Bao gồm getter, setter, toString, equals, hashCode
@NoArgsConstructor  // Constructor không tham số

public class OrderDetail {

    @EmbeddedId
    private OrderDetailId id;

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

        private BigInteger idOrder;
        private BigInteger idProduct;
    }
}
