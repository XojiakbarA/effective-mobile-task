package org.example.effectivemobiletask.service.impl;

import org.example.effectivemobiletask.dto.request.EmailRequest;
import org.example.effectivemobiletask.dto.view.EmailDTO;
import org.example.effectivemobiletask.entity.Email;
import org.example.effectivemobiletask.entity.User;
import org.example.effectivemobiletask.exception.OperationIsNotPossibleException;
import org.example.effectivemobiletask.exception.ResourceExistsException;
import org.example.effectivemobiletask.exception.ResourceNotFoundException;
import org.example.effectivemobiletask.repository.EmailRepository;
import org.example.effectivemobiletask.service.EmailService;
import org.example.effectivemobiletask.service.UserService;
import org.example.effectivemobiletask.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    private final String RESOURCE_NAME = Email.class.getSimpleName();

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    private Mapper mapper;

    @Override
    public boolean existsByValue(String value) {
        return emailRepository.existsByValue(value);
    }

    @Override
    public List<EmailDTO> getAllByUserUsername(String username) {
        return emailRepository.findAllByUserUsername(username).stream().map(mapper::toDTO).toList();
    }

    @Override
    public EmailDTO createByUserUsername(String username, EmailRequest request) {
        if (existsByValue(request.getEmail())) {
            throw new ResourceExistsException(RESOURCE_NAME, "email", request.getEmail());
        }

        User user = userService.findByUsername(username);

        Email email = new Email();
        email.setUser(user);
        email.setValue(request.getEmail());

        return mapper.toDTO(emailRepository.save(email));
    }

    @Override
    public EmailDTO updateByIdAndUserUsername(Long id, String username, EmailRequest request) {
        Email email = emailRepository.findByIdAndUserUsername(id, username).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NAME, "id+username", id + "+" + username)
        );

        if (emailRepository.existsByValueAndIdNot(request.getEmail(), id)) {
            throw new ResourceExistsException(RESOURCE_NAME, "email", request.getEmail());
        }

        email.setValue(request.getEmail());

        return mapper.toDTO(emailRepository.save(email));
    }

    @Override
    public void deleteByIdAndUserUsername(Long id, String username) {
        User user = userService.findByUsername(username);

        if (user.getPhones().size() == 1) {
            throw new OperationIsNotPossibleException("At least one phone number must remain");
        }

        user.getEmails().remove(findById(id));

        emailRepository.deleteByIdAndUserUsername(id, username);
    }

    private Email findById(Long id) {
        return emailRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NAME, "id", id)
        );
    }
}
