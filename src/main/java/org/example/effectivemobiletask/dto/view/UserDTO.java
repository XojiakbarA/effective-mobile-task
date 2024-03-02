package org.example.effectivemobiletask.dto.view;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String fullName;
    private Date birthDate;
    private Set<PhoneDTO> phones = new HashSet<>();
    private Set<EmailDTO> emails = new HashSet<>();
}
