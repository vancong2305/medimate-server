package com.example.medimateserver.service;

import com.example.medimateserver.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {

    List<Order> findAll();

    Order findById(Integer id);

    Order save(Order order);

    Order update(Integer id, Order order);

    void deleteById(Integer id);
}
