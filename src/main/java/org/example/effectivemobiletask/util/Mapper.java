package org.example.effectivemobiletask.util;

import org.example.effectivemobiletask.dto.view.PhoneDTO;
import org.example.effectivemobiletask.entity.Phone;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public PhoneDTO toDTO(Phone phone) {
        PhoneDTO dto = new PhoneDTO();

        dto.setId(phone.getId());
        dto.setNumber(phone.getNumber());

        return dto;
    }
}
