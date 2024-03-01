package org.example.effectivemobiletask.service.impl;

import org.example.effectivemobiletask.entity.Account;
import org.example.effectivemobiletask.repository.AccountRepository;
import org.example.effectivemobiletask.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }
}
