package org.example.effectivemobiletask.service;

import org.example.effectivemobiletask.dto.request.EmailRequest;
import org.example.effectivemobiletask.dto.view.EmailDTO;

import java.util.List;

public interface EmailService {
    boolean existsByValue(String value);

    List<EmailDTO> getAllByUserUsername(String username);

    EmailDTO createByUserUsername(String username, EmailRequest request);

    EmailDTO updateByIdAndUserUsername(Long id, String username, EmailRequest request);

    void deleteByIdAndUserUsername(Long id, String username);
}
