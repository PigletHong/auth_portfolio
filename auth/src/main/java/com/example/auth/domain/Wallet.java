package com.example.auth.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_auth_wallet")
@IdClass(WalletKey.class)
public class Wallet {
    @Id
    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Id
    @Column(name = "network", nullable = false)
    private String network;

    @Id
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "account_key", nullable = false)
    private String accountKey;

    @Column(name = "link_key", nullable = false)
    private String linkKey;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;
}
