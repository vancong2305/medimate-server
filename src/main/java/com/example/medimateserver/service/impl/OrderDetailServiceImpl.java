package com.example.medimateserver.service.impl;

import com.example.medimateserver.dto.CategoryDto;
import com.example.medimateserver.dto.OrderDetailDto;
import com.example.medimateserver.dto.OrderDto;
import com.example.medimateserver.entity.Order;
import com.example.medimateserver.entity.OrderDetail;
import com.example.medimateserver.repository.OrderDetailRepository;
import com.example.medimateserver.service.OrderDetailService;
import com.example.medimateserver.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetailDto> findAll() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findAll();
        return orderDetailList.stream()
                .map(orderDetail -> ConvertUtil.gI().toDto(orderDetailList, OrderDetailDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDto findById(Integer id) {
        return orderDetailRepository.findById(id)
                .map(orderDetail -> ConvertUtil.gI().toDto(orderDetail, OrderDetailDto.class))
                .orElse(null);
    }


    @Override
    public OrderDetailDto save(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = ConvertUtil.gI().toEntity(orderDetailDto, OrderDetail.class);
        orderDetail = orderDetailRepository.save(orderDetail);
        return ConvertUtil.gI().toDto(orderDetail, OrderDetailDto.class);
    }

    @Override
    public OrderDetailDto update(Integer id, OrderDetailDto orderDetail) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
