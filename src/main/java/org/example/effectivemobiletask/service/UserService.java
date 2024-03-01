package org.example.effectivemobiletask.service;

import org.example.effectivemobiletask.dto.request.LoginRequest;
import org.example.effectivemobiletask.dto.request.RegisterRequest;
import org.example.effectivemobiletask.entity.User;

public interface UserService {
    String login(LoginRequest request);

    void register(RegisterRequest request);

    User findByUsername(String username);
}
