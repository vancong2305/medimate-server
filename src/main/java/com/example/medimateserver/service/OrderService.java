package com.example.medimateserver.service;

import com.example.medimateserver.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {

    List<OrderDto> findAll();

    OrderDto findById(Integer id);

    OrderDto save(OrderDto order);

    OrderDto update(Integer id, OrderDto order);

    void deleteById(Integer id);

    List<OrderDto> findByIdUser(Integer id);
}
