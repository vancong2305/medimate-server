package com.example.medimateserver.service.impl;

import com.example.medimateserver.dto.OrderDetailDto;
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
    public List<OrderDetailDto> findByOrderId(Integer id) {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByIdOrder(id);
        System.out.println(orderDetailList.get(0).toString());
        return orderDetailList.stream()
                .map(orderDetail -> ConvertUtil.gI().toDto(orderDetail, OrderDetailDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDto> findAll() {
        return null;
    }

    public OrderDetailDto toDto(OrderDetail orderDetail) {
        OrderDetailDto dto = new OrderDetailDto();
        dto.setIdOrder(orderDetail.getIdOrder());
        dto.setIdProduct(orderDetail.getIdProduct());
        dto.setDiscountPrice(orderDetail.getDiscountPrice());
        dto.setQuantity(orderDetail.getQuantity());
        return dto;
    }
}
