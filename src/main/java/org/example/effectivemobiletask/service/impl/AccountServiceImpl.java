package org.example.effectivemobiletask.service.impl;

import org.example.effectivemobiletask.dto.request.TransferRequest;
import org.example.effectivemobiletask.entity.Account;
import org.example.effectivemobiletask.entity.Phone;
import org.example.effectivemobiletask.exception.OperationIsNotPossibleException;
import org.example.effectivemobiletask.exception.ResourceNotFoundException;
import org.example.effectivemobiletask.repository.AccountRepository;
import org.example.effectivemobiletask.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final String RESOURCE_NAME = Account.class.getSimpleName();

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

    @Override
    @Transactional
    public void transfer(String username, TransferRequest request) {
        Double amount = request.getAmount();
        Account sender = findByUsername(username);
        Account receiver = findByUsername(request.getUsername());

        if (amount > sender.getAmount()) {
            throw new OperationIsNotPossibleException("Not enough in the balance");
        }

        sender.setAmount(sender.getAmount() - amount);
        receiver.setAmount(receiver.getAmount() + amount);

        accountRepository.saveAll(List.of(sender, receiver));
    }

    private Account findByUsername(String username) {
        return accountRepository.findByUserUsername(username).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NAME, "username", username)
        );
    }
}
