package com.example.medimateserver.service.impl;

import com.example.medimateserver.model.User;
import com.example.medimateserver.repository.UserRepository;
import com.example.medimateserver.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
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
    public User findById(BigInteger id) {return null;}

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(BigInteger id, User user) {
        return null;
    }

    @Override
    public void deleteById(BigInteger id) {

    }
    public static User convertToObject(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, User.class);
    }

    public static String convertToJson(User user) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(user);
    }

}
