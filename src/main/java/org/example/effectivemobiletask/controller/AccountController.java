package org.example.effectivemobiletask.controller;

import org.example.effectivemobiletask.dto.request.TransferRequest;
import org.example.effectivemobiletask.dto.response.Response;
import org.example.effectivemobiletask.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    public Response transfer(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody TransferRequest request) {
        accountService.transfer(userDetails.getUsername(), request);

        return new Response(HttpStatus.OK.name());
    }
}
