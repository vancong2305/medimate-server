package com.example.medimateserver.service.impl;

import com.example.medimateserver.model.Role;
import com.example.medimateserver.service.RoleService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role findById(BigInteger id) {
        return null;
    }

    @Override
    public Role findByEmail(String email) {
        return null;
    }

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public Role update(BigInteger id, Role role) {
        return null;
    }

    @Override
    public void deleteById(BigInteger id) {}

}
