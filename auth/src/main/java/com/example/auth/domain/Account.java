package com.example.auth.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_auth_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false)
    private Long uid;

    @Column(name = "account_key", nullable = false)
    private String accountKey;

    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;
}
