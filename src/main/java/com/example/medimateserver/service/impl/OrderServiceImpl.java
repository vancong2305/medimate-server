package com.example.medimateserver.service.impl;

import com.example.medimateserver.entity.Order;
import com.example.medimateserver.repository.OrderRepository;
import com.example.medimateserver.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Integer id) {

        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Integer id, Order order) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    public static Order convertToObject(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, Order.class);
    }

    public static String convertToJson(Order order) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(order);
    }


}
