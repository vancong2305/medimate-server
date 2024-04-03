package com.example.medimateserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.el.stream.Optional;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "point")
    private Integer point;

    @Column(name = "status")
    private Integer status;

    // Không cần constructor có tham số, Lombok sẽ tự động tạo ra
}
