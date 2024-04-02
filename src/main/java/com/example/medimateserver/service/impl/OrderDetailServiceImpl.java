package com.example.medimateserver.service.impl;

import com.example.medimateserver.model.OrderDetail;
import com.example.medimateserver.service.OrderDetailService;
import com.example.medimateserver.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public List<OrderDetail> findAll() {
        return null;
    }

    @Override
    public OrderDetail findById(Integer id) {
        return null;
    }


    @Override
    public OrderDetail findByEmail(String email) {
        return null;
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public OrderDetail update(Integer id, OrderDetail orderDetail) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
