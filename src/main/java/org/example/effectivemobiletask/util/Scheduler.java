package org.example.effectivemobiletask.util;

import org.example.effectivemobiletask.entity.Account;
import org.example.effectivemobiletask.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
    private final double interestRate = 0.05;
    private final double limitRate = 2.07;

    @Autowired
    private AccountService accountService;

    @Scheduled(fixedRate = 1000 * 60)
    public void increaseBalance() {
        List<Account> accounts = accountService.findAll();
        accounts.forEach(account -> {
            double initAmount = account.getInitAmount();
            double amount = account.getAmount();
            double currentAmount = amount + (amount * interestRate);

            if (currentAmount / initAmount < limitRate) {
                account.setAmount(currentAmount);
                accountService.save(account);

                logger.info("Balance increased from " + amount + " to " + currentAmount + " - " + account);
            }
        });
    }
}
