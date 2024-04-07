package com.example.medimateserver.controller.api;

import com.example.medimateserver.dto.OrderDto;
import com.example.medimateserver.entity.Orders;
import com.example.medimateserver.service.OrderService;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.service.UnitService;
import com.example.medimateserver.util.GsonUtil;
import com.example.medimateserver.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/orders", produces = "application/json")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    private UnitService userService;
    @Autowired
    private TokenService tokenService;
    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ResponseUtil.success(GsonUtil.gI().toJson(orderService.findAll()));
    }
    @PostMapping("/{id}")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto savedOrder = orderService.save(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable String code, @RequestBody Orders Orders) {
        return ResponseEntity.ok("updated Order");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String code) {
        // ... (Implement delete logic with OrderService)
        return ResponseEntity.noContent().build();
    }
}
