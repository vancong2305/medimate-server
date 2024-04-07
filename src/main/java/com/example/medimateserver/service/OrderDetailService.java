package com.example.medimateserver.service;

import com.example.medimateserver.dto.OrderDetailDto;
import com.example.medimateserver.entity.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderDetailService {
    List<OrderDetailDto> findByOrderId(Integer id);

    List<OrderDetailDto> findAll();
}
