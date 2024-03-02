package org.example.effectivemobiletask.util;

import org.example.effectivemobiletask.dto.request.RegisterRequest;
import org.example.effectivemobiletask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.register(getRegisterRequest(
                "xoji",
                "123",
                "Akramov Khojiakbar Makhmud-ugli",
                2000D,
                "xoji@mail.com",
                "998909118611"
        ));
        userService.register(getRegisterRequest(
                "zari",
                "321",
                "Akramova Zarina Rustam-qizi",
                1500D,
                "zari@mail.com",
                "998900088611"
        ));
    }

    private RegisterRequest getRegisterRequest(String username, String password, String fullName, double initAmount, String email, String phoneNumber) {
        RegisterRequest request = new RegisterRequest();

        request.setUsername(username);
        request.setPassword(password);
        request.setFullName(fullName);
        request.setInitAmount(initAmount);
        request.setEmail(email);
        request.setPhoneNumber(phoneNumber);

        return request;
    }
}
