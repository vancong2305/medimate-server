package com.example.medimateserver.controller.web;

import com.example.medimateserver.config.payment.momo.model.MomoCreateRequest;
import com.example.medimateserver.config.payment.momo.shared.Encoder;
import com.example.medimateserver.config.payment.momo.shared.Parameter;
import com.example.medimateserver.service.UserService;
import com.example.medimateserver.util.GsonUtil;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@RestController
public class Test {
    @Autowired
    UserService userService;
    @GetMapping("/test")
    public String test() throws Exception {
        String orderId = String.valueOf(System.currentTimeMillis());
        String requestId = String.valueOf(System.currentTimeMillis());
        System.out.println("Vào đây nè!");
        MomoCreateRequest createReq = new MomoCreateRequest();
        createReq.setOrderInfo("Pay With MoMo");
        createReq.setAmount(100000);
        createReq.setPartnerName("test MoMo");
        createReq.setRequestType("captureWallet");
        createReq.setRedirectUrl("https://google.com.vn");
        createReq.setIpnUrl("https://google.com.vn");
        createReq.setStoreId("test store ID");
        createReq.setExtraData("");
        createReq.setAutoCapture(true);
        createReq.setPartnerCode("MOMOLRJZ20181206");
        createReq.setRequestId(requestId);
        createReq.setOrderId(orderId);
        createReq.setLang("en");
        createReq.setStartTime(System.currentTimeMillis());
        String requestRawData = new StringBuilder()
                .append(Parameter.ACCESS_KEY).append("=").append(Parameter.DEV_ACCESS_KEY).append("&")
                .append(Parameter.AMOUNT).append("=").append(Long.toString(createReq.getAmount())).append("&")
                .append(Parameter.EXTRA_DATA).append("=").append("").append("&")
                .append(Parameter.IPN_URL).append("=").append(createReq.getIpnUrl()).append("&")
                .append(Parameter.ORDER_ID).append("=").append(createReq.getOrderId()).append("&")
                .append(Parameter.ORDER_INFO).append("=").append(createReq.getOrderInfo()).append("&")
                .append(Parameter.PARTNER_CODE).append("=").append(createReq.getPartnerCode()).append("&")
                .append(Parameter.REDIRECT_URL).append("=").append(createReq.getRedirectUrl()).append("&")
                .append(Parameter.REQUEST_ID).append("=").append(createReq.getRequestId()).append("&")
                .append(Parameter.REQUEST_TYPE).append("=").append(createReq.getRequestType())
                .toString();
        String signRequest = Encoder.signHmacSHA256(requestRawData, Parameter.DEV_SECRET_KEY);
        createReq.setSignature(signRequest);
        String payload = GsonUtil.gI().toJson(createReq);
        return sendRequest(payload);
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
