package org.example.effectivemobiletask.service.impl;

import org.example.effectivemobiletask.repository.EmailRepository;
import org.example.effectivemobiletask.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailRepository emailRepository;

    @Override
    public boolean existsByValue(String value) {
        return emailRepository.existsByValue(value);
    }
}
