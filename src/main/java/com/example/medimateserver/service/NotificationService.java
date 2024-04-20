package com.example.medimateserver.service;

import com.example.medimateserver.dto.DeviceDto;
import com.example.medimateserver.dto.NotificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    List<NotificationDto> findByIdUser(Integer id);
    NotificationDto save(NotificationDto notificationDto);
    boolean changeAllStatus(Integer id);
}
