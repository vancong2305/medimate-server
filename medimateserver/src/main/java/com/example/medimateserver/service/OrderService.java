package com.example.medimateserver.service;

import com.example.medimateserver.model.Order;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public interface OrderService {

    List<Order> findAll();

    Order findById(BigInteger id);
    Order findByEmail(String email);

    Order save(Order order);

    Order update(BigInteger id, Order order);

    void deleteById(BigInteger id);
}
