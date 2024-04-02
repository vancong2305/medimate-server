package com.example.medimateserver.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor // Thêm constructor không tham số
public class UserDto {
    private Integer id;
    private String password;
    private String email;
    private Integer idRole;
    private Integer point;
    private Integer status;

}
