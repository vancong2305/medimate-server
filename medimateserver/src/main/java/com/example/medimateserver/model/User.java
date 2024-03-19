package com.example.medimateserver.model;

import jakarta.persistence.*;
import java.math.BigInteger;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "id_role")
    private BigInteger idRole;

    @Column(name = "point")
    private BigInteger point;
    @Column(name = "status")
    private Integer status;

    // Không cần constructor có tham số, Lombok sẽ tự động tạo ra
}
