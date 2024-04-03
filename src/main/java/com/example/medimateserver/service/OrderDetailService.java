package com.example.medimateserver.service;

import com.example.medimateserver.entity.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderDetailService {

    List<OrderDetail> findAll();

    OrderDetail findById(Integer id);

    OrderDetail save(OrderDetail orderDetail);

    OrderDetail update(Integer id, OrderDetail orderDetail);

    void deleteById(Integer id);
}
