package org.example.effectivemobiletask.service.impl;

import org.example.effectivemobiletask.dto.request.PhoneRequest;
import org.example.effectivemobiletask.dto.view.PhoneDTO;
import org.example.effectivemobiletask.entity.Phone;
import org.example.effectivemobiletask.entity.User;
import org.example.effectivemobiletask.exception.OperationIsNotPossibleException;
import org.example.effectivemobiletask.exception.ResourceExistsException;
import org.example.effectivemobiletask.exception.ResourceNotFoundException;
import org.example.effectivemobiletask.repository.PhoneRepository;
import org.example.effectivemobiletask.service.PhoneService;
import org.example.effectivemobiletask.service.UserService;
import org.example.effectivemobiletask.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {
    private final String RESOURCE_NAME = Phone.class.getSimpleName();

    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    private Mapper mapper;

    @Override
    public boolean existsByNumber(String number) {
        return phoneRepository.existsByNumber(number);
    }

    @Override
    public List<PhoneDTO> getAllByUserUsername(String username) {
        return phoneRepository.getAllByUserUsername(username).stream().map(mapper::toDTO).toList();
    }

    @Override
    public PhoneDTO createByUserUsername(String username, PhoneRequest request) {
        if (existsByNumber(request.getPhoneNumber())) {
            throw new ResourceExistsException(RESOURCE_NAME, "phoneNumber", request.getPhoneNumber());
        }

        User user = userService.findByUsername(username);

        Phone phone = new Phone();
        phone.setUser(user);
        phone.setNumber(request.getPhoneNumber());

        return mapper.toDTO(phoneRepository.save(phone));
    }

    @Override
    public PhoneDTO updateByIdAndUserUsername(Long id, String username, PhoneRequest request) {
        Phone phone = phoneRepository.findByIdAndUserUsername(id, username).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NAME, "id+username", id + "+" + username)
        );

        if (phoneRepository.existsByNumberAndIdNot(request.getPhoneNumber(), id)) {
            throw new ResourceExistsException(RESOURCE_NAME, "phoneNumber", request.getPhoneNumber());
        }

        phone.setNumber(request.getPhoneNumber());

        return mapper.toDTO(phoneRepository.save(phone));
    }

    @Override
    public void deleteByIdAndUserUsername(Long id, String username) {
        User user = userService.findByUsername(username);

        if (user.getPhones().size() == 1) {
            throw new OperationIsNotPossibleException("At least one phone number must remain");
        }

        user.getPhones().remove(findById(id));

        phoneRepository.deleteByIdAndUserUsername(id, username);
    }

    private Phone findById(Long id) {
        return phoneRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NAME, "id", id)
        );
    }
}
