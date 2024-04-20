package com.example.medimateserver.controller.api;

import com.example.medimateserver.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/notification", produces = "application/json")
public class NotificationController {
    @GetMapping
    public ResponseEntity<?> getAllNotification(HttpServletRequest request) {
        try {

        } catch (Exception ex) {
            return ResponseUtil.failed();
        }
        return ResponseUtil.success();
    }
    @PostMapping
    public ResponseEntity<?> updateNotification(HttpServletRequest request) {
        try {

        } catch (Exception ex) {
            return ResponseUtil.failed();
        }
        return ResponseUtil.success();
    }
}
