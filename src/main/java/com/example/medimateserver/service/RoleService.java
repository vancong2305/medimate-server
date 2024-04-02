package com.example.medimateserver.service;

import com.example.medimateserver.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleService {

    List<Role> findAll();

    Role findById(Integer id);
    Role findByEmail(String email);

    Role save(Role role);

    Role update(Integer id, Role role);

    void deleteById(Integer id);
}
