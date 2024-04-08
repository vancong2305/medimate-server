package com.example.medimateserver.service.impl;

import com.example.medimateserver.dto.OrderDetailDto;
import com.example.medimateserver.dto.OrderDto;
import com.example.medimateserver.dto.PaymentDto;
import com.example.medimateserver.dto.ProductDto;
import com.example.medimateserver.entity.*;
import com.example.medimateserver.repository.*;
import com.example.medimateserver.service.OrderService;
import com.example.medimateserver.util.ConvertUtil;
import com.example.medimateserver.util.GsonUtil;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CouponDetailRepository couponDetailRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<OrderDto> findAll() {
        List<Orders> ordersList = orderRepository.findAll();
        return ordersList.stream()
                .map(order -> ConvertUtil.gI().toDto(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(Integer id) {

        return orderRepository.findById(id)
                .map(order -> ConvertUtil.gI().toDto(order, OrderDto.class))
                .orElse(null);
    }
    @Transactional
    @Override
    public OrderDto save(PaymentDto paymentDto) {
            Integer sum = 0;
            for (OrderDetailDto orderDetailDto : paymentDto.getOrderDetailDtoList()) {
                Optional<Product> product = productRepository.findById(orderDetailDto.getIdProduct());
                orderDetailDto.setIdOrder(null);
                orderDetailDto.setDiscountPrice(Integer.parseInt(product.get().getPrice() * product.get().getDiscountPercent() / 100 + ""));
                if (!product.isPresent() || orderDetailDto.getQuantity() > product.get().getQuantity() && product.get().getStatus() == 0) {
                    throw new IllegalArgumentException("Sản phẩm không đủ hoặc không bán nữa " + GsonUtil.gI().toJson(product.get()));
                }
                sum += orderDetailDto.getQuantity() * product.get().getPrice() - orderDetailDto.getDiscountPrice();
            }

            Optional<CouponDetail> couponDetail = couponDetailRepository.findById(paymentDto.getCouponDetail().getId());
            if (couponDetail.get().getIdUser() != paymentDto.getIdUser() || couponDetail.get().getStatus() == 0) {
                throw new IllegalArgumentException("Khuyến mãi hết hạn hoặc không đúng!");
            }
            Date date = couponDetail.get().getEndTime();
            Date now = new Date();
            boolean isAfter = date.after(now);
            if (!isAfter) {
                throw new IllegalArgumentException("Khuyến mãi hết hạn!");
            }

           Integer discountCoupon = sum * couponDetail.get().getCoupon().getDiscountPercent() / 100;
            sum -= discountCoupon;
            Integer point = sum / 1000;

            Optional<User> user = userRepository.findById(paymentDto.getIdUser());
            User savedUser = user.get();

            Orders order = new Orders();
            String code = savedUser.getId()+now.getTime()+"";
            order.setCode("MDH"+code.hashCode()+"");
            order.setIdUser(paymentDto.getIdUser());
            order.setDiscountCoupon(discountCoupon);
            order.setOrderTime(now);
            order.setPoint(point);
            order.setNote(paymentDto.getNote());
            order.setPaymentMethod(1);
            order.setStatus(1);
            order = orderRepository.save(order);

            List<Product> productList = new ArrayList<>();

            for (OrderDetailDto orderDetailDto : paymentDto.getOrderDetailDtoList()) {
                Optional<Product> product = productRepository.findById(orderDetailDto.getIdProduct());
                product.get().setQuantity(product.get().getQuantity() - orderDetailDto.getQuantity());
                productList.add(product.get());
                orderDetailRepository.saveCustome(order.getId(), product.get().getId(), orderDetailDto.getDiscountPrice(), orderDetailDto.getQuantity());
            }
            productRepository.saveAll(productList);

            CouponDetail savedCoupon = couponDetail.get();
            savedCoupon.setIdOrder(order.getId());
            savedCoupon.setStatus(0);
            couponDetailRepository.save(savedCoupon);

            System.out.println(paymentDto.getIdUser());
            savedUser.setPoint(savedUser.getPoint()+order.getPoint());
            userRepository.save(savedUser);

            return ConvertUtil.gI().toDto(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> findByIdUser(Integer id) {
        List<Orders> ordersList = orderRepository.findByIdUser(id);
        return ordersList
                .stream()
                .filter(order -> order.getStatus() != 0) // Filter out orderes with status 0
                .map(order -> ConvertUtil.gI().toDto(order, OrderDto.class))
                .collect(Collectors.toList());
    }


}
