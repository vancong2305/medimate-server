package com.example.medimateserver.repository;

import com.example.medimateserver.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetail.OrderDetailId> {
    @Query("SELECT od FROM OrderDetail od WHERE od.id.idOrder = :idOrder")
    List<OrderDetail> findByIdOrder(@Param("idOrder") Integer idOrder);

}
