package com.example.auth.repository;

import com.example.auth.domain.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> getAccount(String accountKey);
    Account saveAccount(Account account);
}
