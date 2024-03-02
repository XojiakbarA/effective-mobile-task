package org.example.effectivemobiletask.service;

import org.example.effectivemobiletask.dto.request.FilterRequest;
import org.example.effectivemobiletask.dto.request.LoginRequest;
import org.example.effectivemobiletask.dto.request.RegisterRequest;
import org.example.effectivemobiletask.dto.view.UserDTO;
import org.example.effectivemobiletask.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    String login(LoginRequest request);

    void register(RegisterRequest request);

    User findByUsername(String username);

    Page<UserDTO> getAllByFilter(List<FilterRequest> filters, int page, int size, String sortBy, String dir);
}
