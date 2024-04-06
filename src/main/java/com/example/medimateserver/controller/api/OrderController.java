package com.example.medimateserver.controller.api;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.dto.OrderDto;
import com.example.medimateserver.dto.OrderDto;
import com.example.medimateserver.dto.TokenDto;
import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.entity.Order;
import com.example.medimateserver.entity.Order;
import com.example.medimateserver.service.OrderService;
import com.example.medimateserver.service.OrderService;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.service.UnitService;
import com.example.medimateserver.util.GsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order", produces = "application/json")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    private UnitService userService;
    @Autowired
    private TokenService tokenService;
    @GetMapping
    public ResponseEntity<?> getAllOrders(HttpServletRequest request) {
        try {
            String tokenInformation = request.getHeader("Authorization");
            tokenInformation = tokenInformation.substring(7);
            UserDto user = GsonUtil.gI().fromJson(JwtProvider.getUsernameFromToken(tokenInformation), UserDto.class);
            TokenDto tokenDto = tokenService.findById(user.getId());
            if (JwtProvider.verifyToken(tokenInformation, tokenDto)) {
                return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST.getReasonPhrase() + " Wrong token!", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>("Errorr: " + ex.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto OrderDto) {
        OrderDto savedOrder = orderService.save(OrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable String code, @RequestBody Order Order) {
        return ResponseEntity.ok("updated Order");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String code) {
        // ... (Implement delete logic with OrderService)
        return ResponseEntity.noContent().build();
    }
}
