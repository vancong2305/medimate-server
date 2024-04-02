package com.example.medimateserver.service.impl;

import com.example.medimateserver.model.OrderDetail;
import com.example.medimateserver.repository.OrderDetailRepository;
import com.example.medimateserver.service.OrderDetailService;
import com.example.medimateserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail findById(Integer id) {
        return orderDetailRepository.findById(id).orElse(null);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail update(Integer id, OrderDetail orderDetail) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
