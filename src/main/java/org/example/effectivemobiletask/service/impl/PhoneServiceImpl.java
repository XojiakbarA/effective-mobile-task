package org.example.effectivemobiletask.service.impl;

import org.example.effectivemobiletask.repository.PhoneRepository;
import org.example.effectivemobiletask.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public boolean existsByNumber(String number) {
        return phoneRepository.existsByNumber(number);
    }
}
