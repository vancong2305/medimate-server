package com.example.medimateserver.service;

import com.example.medimateserver.model.OrderDetail;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public interface OrderDetailService {

    List<OrderDetail> findAll();

    OrderDetail findById(BigInteger id);
    OrderDetail findByEmail(String email);

    OrderDetail save(OrderDetail orderDetail);

    OrderDetail update(BigInteger id, OrderDetail orderDetail);

    void deleteById(BigInteger id);
}
