package org.example.effectivemobiletask.util;

import org.example.effectivemobiletask.dto.view.EmailDTO;
import org.example.effectivemobiletask.dto.view.PhoneDTO;
import org.example.effectivemobiletask.dto.view.UserDTO;
import org.example.effectivemobiletask.entity.Email;
import org.example.effectivemobiletask.entity.Phone;
import org.example.effectivemobiletask.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {
    public PhoneDTO toDTO(Phone phone) {
        PhoneDTO dto = new PhoneDTO();

        dto.setId(phone.getId());
        dto.setNumber(phone.getNumber());

        return dto;
    }

    public EmailDTO toDTO(Email email) {
        EmailDTO dto = new EmailDTO();

        dto.setId(email.getId());
        dto.setValue(email.getValue());

        return dto;
    }

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setBirthDate(user.getBirthDate());
        dto.setPhones(user.getPhones().stream().map(this::toDTO).collect(Collectors.toSet()));
        dto.setEmails(user.getEmails().stream().map(this::toDTO).collect(Collectors.toSet()));

        return dto;
    }
}
