package com.example.auth.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_auth_guest")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Guest {
    @Id
    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "account_key", nullable = false)
    private String accountKey;

    @Column(name = "link_key", nullable = false)
    private String linkKey;

    @Column(name = "reg_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime regDate;
}

