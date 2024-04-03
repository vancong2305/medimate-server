package com.example.medimateserver.service.impl;

import com.example.medimateserver.entity.Role;
import com.example.medimateserver.repository.RoleRepository;
import com.example.medimateserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Integer id, Role role) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {}

}
