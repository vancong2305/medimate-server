package com.example.medimateserver.controller.api;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.dto.OrderDetailDto;
import com.example.medimateserver.dto.OrderDto;
import com.example.medimateserver.dto.TokenDto;
import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.entity.OrderDetail;
import com.example.medimateserver.service.OrderDetailService;
import com.example.medimateserver.service.OrderService;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.service.UserService;
import com.example.medimateserver.util.GsonUtil;
import com.example.medimateserver.util.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order_detail", produces = "application/json")
public class OrderDetailController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllOrderDetail(HttpServletRequest request, @PathVariable Integer id) throws JsonProcessingException {
        try {
            String tokenInformation = request.getHeader("Authorization").substring(7);
            UserDto user = GsonUtil.gI().fromJson(JwtProvider.getUsernameFromToken(tokenInformation), UserDto.class);
            TokenDto tokenDto = tokenService.findById(user.getId());
            if (JwtProvider.verifyToken(tokenInformation, tokenDto)) {
                List<OrderDetailDto> orderDetailList = orderDetailService.findByIdUser(id);
                String jsons = GsonUtil.gI().toJson(orderDetailList);
                return ResponseUtil.success(jsons);
            }
            return ResponseUtil.failed();
        } catch (Exception ex) {
            System.out.println("Lỗi ở đây " + ex.getMessage());
            return ResponseUtil.failed();
        }
    }
}
