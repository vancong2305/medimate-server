package com.example.medimateserver.service.impl;

import com.example.medimateserver.model.Order;
import com.example.medimateserver.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order findById(BigInteger id) {
        return null;
    }

    @Override
    public Order findByEmail(String email) {
        return null;
    }

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public Order update(BigInteger id, Order order) {
        return null;
    }

    @Override
    public void deleteById(BigInteger id) {

    }


}
