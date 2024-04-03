package com.example.medimateserver.service.impl;

import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.entity.User;
import com.example.medimateserver.repository.UserRepository;
import com.example.medimateserver.service.UserService;
import com.example.medimateserver.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> ConvertUtil.gI().toDto(user, UserDto.class))
                .orElse(null); // Trả về null nếu không tìm thấy optional
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


}