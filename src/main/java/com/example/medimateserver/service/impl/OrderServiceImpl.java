package com.example.medimateserver.service.impl;

import com.example.medimateserver.dto.CategoryDto;
import com.example.medimateserver.dto.OrderDto;
import com.example.medimateserver.entity.Category;
import com.example.medimateserver.entity.Order;
import com.example.medimateserver.repository.OrderRepository;
import com.example.medimateserver.service.OrderService;
import com.example.medimateserver.util.ConvertUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderDto> findAll() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream()
                .map(order -> ConvertUtil.gI().toDto(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(Integer id) {

        return orderRepository.findById(id)
                .map(order -> ConvertUtil.gI().toDto(order, OrderDto.class))
                .orElse(null);
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = ConvertUtil.gI().toEntity(orderDto, Order.class);
        order = orderRepository.save(order);
        return ConvertUtil.gI().toDto(order, OrderDto.class);
    }

    @Override
    public OrderDto update(Integer id, OrderDto orderDto) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
