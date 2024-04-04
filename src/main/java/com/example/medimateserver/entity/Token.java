package com.example.medimateserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "token")
@Data
@NoArgsConstructor // Thêm constructor không tham số
public class Token {
    @Id
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "access_token")
    private String accessToken;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
