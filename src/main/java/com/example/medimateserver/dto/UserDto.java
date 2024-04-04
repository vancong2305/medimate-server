package com.example.medimateserver.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor // Thêm constructor không tham số
public class UserDto {
    private Integer id;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*-_=<>?]).{8,32}$", message = "Mật khẩu phải bao gồm ít nhất một chữ hoa, một chữ số, một ký tự đặc biệt và dài 8-32 ký tự")
    private String password;
    @Email(message = "Không hợp lệ")
    private String email;
    private Integer idRole;
    private Integer point;
    private Integer status;

}
