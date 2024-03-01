package org.example.effectivemobiletask.service;

import org.example.effectivemobiletask.dto.request.PhoneRequest;
import org.example.effectivemobiletask.dto.view.PhoneDTO;

import java.util.List;

public interface PhoneService {
    boolean existsByNumber(String number);

    List<PhoneDTO> getAllByUserUsername(String username);

    PhoneDTO createByUserUsername(String username, PhoneRequest request);

    PhoneDTO updateByIdAndUserUsername(Long id, String username, PhoneRequest request);

    void deleteByIdAndUserUsername(Long id, String username);
}
