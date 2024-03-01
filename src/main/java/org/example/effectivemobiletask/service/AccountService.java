package org.example.effectivemobiletask.service;

import org.example.effectivemobiletask.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    void save(Account account);
}
