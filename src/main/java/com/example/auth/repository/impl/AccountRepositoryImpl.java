package com.example.auth.repository.impl;

import com.example.auth.domain.Account;
import com.example.auth.repository.AccountRepository;
import com.example.auth.repository.jpa.AccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Optional<Account> getAccount(String accountKey) {
        return accountJpaRepository.findByAccountKey(accountKey);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountJpaRepository.save(account);
    }
}
