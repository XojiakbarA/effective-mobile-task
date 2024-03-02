package org.example.effectivemobiletask.service.impl;

import org.example.effectivemobiletask.dto.request.TransferRequest;
import org.example.effectivemobiletask.entity.Account;
import org.example.effectivemobiletask.entity.User;
import org.example.effectivemobiletask.exception.OperationIsNotPossibleException;
import org.example.effectivemobiletask.exception.ResourceNotFoundException;
import org.example.effectivemobiletask.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl accountService;
    @Mock
    private AccountRepository accountRepository;

    private Account senderAccount;
    private Account receiverAccount;
    private TransferRequest request;

    @BeforeEach
    public void setup() {
        User sender = new User();
        User receiver = new User();

        senderAccount = new Account();
        receiverAccount = new Account();
        request = new TransferRequest();

        sender.setUsername("sender");

        senderAccount.setUser(sender);
        senderAccount.setAmount(1000D);

        receiver.setUsername("receiver");

        receiverAccount.setUser(receiver);
        receiverAccount.setAmount(1500D);
    }

    @Test
    public void transfer_wrongSenderUsername_throwsException() {
        request.setUsername("receiver");
        request.setAmount(1000D);

        Mockito.when(accountRepository.findByUserUsername("send")).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> accountService.transfer("send", request));
    }

    @Test
    public void transfer_wrongReceiverUsername_throwsException() {
        request.setUsername("rec");
        request.setAmount(1000D);

        Mockito.when(accountRepository.findByUserUsername("sender")).thenReturn(Optional.of(senderAccount));
        Mockito.when(accountRepository.findByUserUsername("rec")).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> accountService.transfer("sender", request));
    }

    @Test
    public void transfer_whenBalanceIsLow_throwsException() {
        request.setUsername("receiver");
        request.setAmount(1500D);

        Mockito.when(accountRepository.findByUserUsername("sender")).thenReturn(Optional.of(senderAccount));
        Mockito.when(accountRepository.findByUserUsername("receiver")).thenReturn(Optional.of(receiverAccount));

        Assertions.assertThrows(OperationIsNotPossibleException.class,
                () -> accountService.transfer("sender", request));
    }

    @Test
    public void transfer_whenBalanceIsHigh() {
        request.setUsername("receiver");
        request.setAmount(500D);

        Mockito.when(accountRepository.findByUserUsername("sender")).thenReturn(Optional.of(senderAccount));
        Mockito.when(accountRepository.findByUserUsername("receiver")).thenReturn(Optional.of(receiverAccount));

        accountService.transfer("sender", request);

        Assertions.assertEquals(500D, senderAccount.getAmount());
        Assertions.assertEquals(2000D, receiverAccount.getAmount());
    }
}
