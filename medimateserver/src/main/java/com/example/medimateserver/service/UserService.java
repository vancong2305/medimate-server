package com.example.medimateserver.service;

import com.example.medimateserver.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public interface UserService {

    List<User> findAll();

    User findById(BigInteger id);
    User findByEmail(String email);

    User save(User user);

    User update(BigInteger id, User user);

    void deleteById(BigInteger id);
}
