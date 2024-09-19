package com.example.auth.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_auth_email")
public class Email {
    @Id
    @Column(name = "email", nullable = false)
    private String emailAddress;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "account_key", nullable = false)
    private String accountKey;

    @Column(name = "link_key", nullable = false)
    private String linkKey;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;
}
