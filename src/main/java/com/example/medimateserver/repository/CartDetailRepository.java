package com.example.medimateserver.repository;

import com.example.medimateserver.entity.CartDetail;
import com.example.medimateserver.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail,CartDetail.CartDetailId> {
    @Query("SELECT od FROM CartDetail od WHERE od.id.idUser = :idUser")
    List<CartDetail> findByIdUser(@Param("idUser") Integer idUser);
}
