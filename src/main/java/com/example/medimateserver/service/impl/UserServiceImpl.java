package com.example.medimateserver.service.impl;

import com.example.medimateserver.dto.CategoryDto;
import com.example.medimateserver.dto.RoleDto;
import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.entity.Category;
import com.example.medimateserver.entity.Role;
import com.example.medimateserver.entity.User;
import com.example.medimateserver.repository.UserRepository;
import com.example.medimateserver.service.UserService;
import com.example.medimateserver.util.ConvertUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository; // Sử dụng private cho đúng nguyên tắc

    @Override
    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();
        return userList
                .stream()
                .map(user -> ConvertUtil.gI().toDto(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public List<UserDto> findWithPageAndSize(int page, int size) {
        // Tạo Pageable object
        Pageable pageable = PageRequest.of(page, size);
        // Truy vấn dữ liệu
        List<User> users = (List<User>) userRepository.findAll(pageable);
        return users
                .stream()
                .map(user -> ConvertUtil.gI().toDto(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {

        return userRepository.findById(id)
                .map(user -> ConvertUtil.gI().toDto(user, UserDto.class))
                .orElse(null);
    }


    @Override
    public UserDto findByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .map(user -> ConvertUtil.gI().toDto(user, UserDto.class))
                .orElse(null); // Trả về null nếu không tìm thấy optional
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = ConvertUtil.gI().toEntity(userDto, User.class);
        user = userRepository.save(user);
        return ConvertUtil.gI().toDto(user, UserDto.class);
    }

    @Override
    public UserDto update(Integer id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));

        existingUser.setPhone(userDto.getPhone());
        existingUser.setStatus(userDto.getStatus());
        existingUser.setPassword(userDto.getPassword());

        User updatedCategory = userRepository.save(existingUser);
        return ConvertUtil.gI().toDto(updatedCategory, UserDto.class);
    }

    @Override
    public void deleteById(Integer id) {

    }


}