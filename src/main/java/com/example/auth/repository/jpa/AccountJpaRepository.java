package com.example.auth.repository.jpa;

import com.example.auth.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountKey(String accountKey);
}
