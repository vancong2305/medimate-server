package com.example.medimateserver.controller.api;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.config.payment.momo.model.MomoCreateRequest;
import com.example.medimateserver.config.payment.momo.model.MomoCreateResponse;
import com.example.medimateserver.config.payment.momo.shared.Encoder;
import com.example.medimateserver.config.payment.momo.shared.Parameter;
import com.example.medimateserver.dto.*;
import com.example.medimateserver.entity.OrderDetail;
import com.example.medimateserver.entity.Orders;
import com.example.medimateserver.service.OrderService;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.service.UnitService;
import com.example.medimateserver.util.GsonUtil;
import com.example.medimateserver.util.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

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
    public ResponseEntity<?> getAllOrder(HttpServletRequest request) {
        try {
            String tokenInformation = request.getHeader("Authorization").substring(7);
            UserDto user = GsonUtil.gI().fromJson(JwtProvider.gI().getUsernameFromToken(tokenInformation), UserDto.class);
            TokenDto tokenDto = tokenService.findById(user.getId());
            if (JwtProvider.gI().verifyToken(tokenInformation, tokenDto)) {
                return ResponseUtil.success(GsonUtil.gI().toJson(orderService.findByIdUser(user.getId())));
            }
            return ResponseUtil.failed();
        } catch (Exception ex) {
            System.out.println("Lỗi ở đây " + ex.getMessage());
            return ResponseUtil.failed();
        }

    }
    @PostMapping
    public ResponseEntity<?> createOrder(HttpServletRequest request, @RequestBody PaymentDto paymentDto)  throws JsonProcessingException {
        try {
            String tokenInformation = request.getHeader("Authorization").substring(7);
            UserDto user = GsonUtil.gI().fromJson(JwtProvider.gI().getUsernameFromToken(tokenInformation), UserDto.class);
            paymentDto.setIdUser(user.getId());
            if (paymentDto.getOrder().getPaymentMethod().contains("COD")) {
                OrderDto savedOrder = orderService.save(paymentDto);
            } else if (paymentDto.getOrder().getPaymentMethod().contains("MOMO")) {
                OrderDto savedOrder = orderService.save(paymentDto);
                System.out.println(getPayUrl(savedOrder));
                return ResponseUtil.successLink(getPayUrl(savedOrder));
            }
            return ResponseUtil.success();
        } catch (Exception ex) {
            return ResponseUtil.failed();
        }
    }

    public String getPayUrl(OrderDto orderDto) throws Exception {
        MomoCreateRequest.getInstance().setAmount(orderDto.getTotal());
        MomoCreateRequest.getInstance().setRequestId(String.valueOf(System.currentTimeMillis()));
        MomoCreateRequest.getInstance().setOrderId(String.valueOf(System.currentTimeMillis()));
        MomoCreateRequest.getInstance().setStartTime(System.currentTimeMillis());
        MomoCreateRequest.getInstance().setOrderInfo(orderDto.getId() + "");
        String requestRawData = new StringBuilder()
                .append(Parameter.ACCESS_KEY).append("=").append(Parameter.DEV_ACCESS_KEY).append("&")
                .append(Parameter.AMOUNT).append("=").append(Long.toString(MomoCreateRequest.getInstance().getAmount())).append("&")
                .append(Parameter.EXTRA_DATA).append("=").append("").append("&")
                .append(Parameter.IPN_URL).append("=").append(MomoCreateRequest.getInstance().getIpnUrl()).append("&")
                .append(Parameter.ORDER_ID).append("=").append(MomoCreateRequest.getInstance().getOrderId()).append("&")
                .append(Parameter.ORDER_INFO).append("=").append(MomoCreateRequest.getInstance().getOrderInfo()).append("&")
                .append(Parameter.PARTNER_CODE).append("=").append(MomoCreateRequest.getInstance().getPartnerCode()).append("&")
                .append(Parameter.REDIRECT_URL).append("=").append(MomoCreateRequest.getInstance().getRedirectUrl()).append("&")
                .append(Parameter.REQUEST_ID).append("=").append(MomoCreateRequest.getInstance().getRequestId()).append("&")
                .append(Parameter.REQUEST_TYPE).append("=").append(MomoCreateRequest.getInstance().getRequestType())
                .toString();
        String signRequest = Encoder.signHmacSHA256(requestRawData, Parameter.DEV_SECRET_KEY);
        MomoCreateRequest.getInstance().setSignature(signRequest);
        String payload = GsonUtil.gI().toJson(MomoCreateRequest.getInstance());
        MomoCreateResponse momoCreateResponse = GsonUtil.gI().fromJson(sendRequest(payload), MomoCreateResponse.class);
        return momoCreateResponse.getPayUrl();
    }

    public static String sendRequest(String payload) throws IOException {
        URL url = new URL("https://test-payment.momo.vn/v2/gateway/api/create");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(payload.getBytes());
        }

        int responseCode = connection.getResponseCode();
        String responseBody = "";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String input;
            while ((input = br.readLine()) != null) {
                responseBody += input;
            }
        }
        return responseBody;
    }

}
