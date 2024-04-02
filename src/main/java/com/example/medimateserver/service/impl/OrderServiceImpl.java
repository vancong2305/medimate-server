package com.example.medimateserver.service.impl;

import com.example.medimateserver.model.Order;
import com.example.medimateserver.model.User;
import com.example.medimateserver.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order findById(Integer id) {
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
