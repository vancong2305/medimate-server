package com.example.medimateserver.service.impl;

import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.fillter.Pagination;
import com.example.medimateserver.model.User;
import com.example.medimateserver.repository.UserRepository;
import com.example.medimateserver.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static Gson gson = new Gson();
    @Autowired
    private UserRepository userRepository; // Sử dụng private cho đúng nguyên tắc

    @Override
    public List<User> findAll() {
        return userRepository.findAll(); // Trả về List trống nếu không có dữ liệu
    }

    public List<User> findWithPageAndSize(int page, int size) {
        // Tạo Pageable object
        Pageable pageable = PageRequest.of(page, size);
        // Truy vấn dữ liệu
        List<User> users = (List<User>) userRepository.findAll(pageable);
        return users;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Integer id, User user) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    public static UserDto convertToObject(String jsonString) throws Exception {
        return gson.fromJson(jsonString, UserDto.class);
    }

    public static String convertToJson(User user) throws Exception {
        return gson.toJson(user);
    }

}
