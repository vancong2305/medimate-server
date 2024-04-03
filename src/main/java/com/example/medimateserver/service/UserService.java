package com.example.medimateserver.service;


import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> findAll();
    List<User> findWithPageAndSize(int page, int size);
    User findById(Integer id);
    UserDto findByEmail(String email);

    User save(User user);

    User update(Integer id, User user);

    void deleteById(Integer id);
}