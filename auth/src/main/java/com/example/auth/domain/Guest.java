package com.example.auth.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_auth_guest")
@IdClass(GuestKey.class)
public class Guest {
    @Id
    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Id
    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "account_key", nullable = false)
    private String accountKey;

    @Column(name = "link_key", nullable = false)
    private String linkKey;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;
}

