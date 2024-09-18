package com.example.auth.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_auth_social")
@IdClass(SocialKey.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Social {
    @Id
    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Id
    @Column(name = "provider", nullable = false)
    private String provider;

    @Id
    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Column(name = "account_key", nullable = false)
    private String accountKey;

    @Column(name = "link_key", nullable = false)
    private String linkKey;

    @Column(name = "reg_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime regDate;
}
