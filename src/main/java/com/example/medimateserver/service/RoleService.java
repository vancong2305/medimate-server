package com.example.medimateserver.service;

import com.example.medimateserver.model.Role;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public interface RoleService {

    List<Role> findAll();

    Role findById(BigInteger id);
    Role findByEmail(String email);

    Role save(Role role);

    Role update(BigInteger id, Role role);

    void deleteById(BigInteger id);
}
