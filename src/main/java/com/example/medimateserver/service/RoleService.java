package com.example.medimateserver.service;

import com.example.medimateserver.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleService {

    List<Role> findAll();

    Role findById(Integer id);

    Role save(Role role);

    Role update(Integer id, Role role);

    void deleteById(Integer id);
}
