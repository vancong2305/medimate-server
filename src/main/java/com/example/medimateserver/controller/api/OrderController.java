package com.example.medimateserver.controller.api;

import com.example.medimateserver.entity.Order;
import com.example.medimateserver.service.OrderService;
import com.example.medimateserver.util.GsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order", produces = "application/json")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/{id}")
    public ResponseEntity<String> getCategoryById(@PathVariable Integer id, HttpServletRequest request) throws JsonProcessingException {
//        String pageInformation = request.getHeader("Pagination");
//        String tokenInformation = request.getHeader("Token");
        List<Order> orderList = orderService.findAll();
        String jsons = GsonUtil.gI().toJson(orderList);

        return new ResponseEntity<>(
                jsons,
                HttpStatus.OK
        );
    }
}
