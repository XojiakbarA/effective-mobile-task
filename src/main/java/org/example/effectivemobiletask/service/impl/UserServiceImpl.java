package org.example.effectivemobiletask.service.impl;

import org.example.effectivemobiletask.dto.request.FilterRequest;
import org.example.effectivemobiletask.dto.request.LoginRequest;
import org.example.effectivemobiletask.dto.request.RegisterRequest;
import org.example.effectivemobiletask.dto.view.UserDTO;
import org.example.effectivemobiletask.entity.Account;
import org.example.effectivemobiletask.entity.Email;
import org.example.effectivemobiletask.entity.Phone;
import org.example.effectivemobiletask.entity.User;
import org.example.effectivemobiletask.exception.ResourceExistsException;
import org.example.effectivemobiletask.repository.UserRepository;
import org.example.effectivemobiletask.security.JWTProvider;
import org.example.effectivemobiletask.service.EmailService;
import org.example.effectivemobiletask.service.PhoneService;
import org.example.effectivemobiletask.service.UserService;
import org.example.effectivemobiletask.util.Mapper;
import org.example.effectivemobiletask.util.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final String RESOURCE_NAME = User.class.getSimpleName();

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private Mapper mapper;

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtProvider.generateToken(Map.of("roles", userDetails.getAuthorities()), userDetails);
    }

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResourceExistsException(RESOURCE_NAME, "username", request.getUsername());
        }
        if (phoneService.existsByNumber(request.getPhoneNumber())) {
            throw new ResourceExistsException(RESOURCE_NAME, "phoneNumber", request.getPhoneNumber());
        }
        if (emailService.existsByValue(request.getEmail())) {
            throw new ResourceExistsException(RESOURCE_NAME, "email", request.getEmail());
        }
        User user = new User();

        Phone phone = new Phone();
        phone.setNumber(request.getPhoneNumber());
        phone.setUser(user);

        Email email = new Email();
        email.setValue(request.getEmail());
        email.setUser(user);

        Account account = new Account();
        account.setAmount(request.getInitAmount());
        account.setInitAmount(request.getInitAmount());
        account.setUser(user);

        user.getPhones().add(phone);
        user.getEmails().add(email);
        user.setAccount(account);
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User by username '%s' not found", username))
        );
    }

    @Override
    public Page<UserDTO> getAllByFilter(List<FilterRequest> filters, int page, int size, String sortBy, String dir) {
        if (page < 1) {
            page = 1;
        }
        if (size < 1) {
            size = 10;
        }
        Sort sort = Sort.by(sortBy).ascending();
        if (dir.equals("desc")) {
            sort = Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return userRepository.findAll(UserSpecification.filter(filters), pageable).map(mapper::toDTO);
    }
}
