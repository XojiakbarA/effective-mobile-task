package org.example.effectivemobiletask.controller;

import org.example.effectivemobiletask.dto.request.EmailRequest;
import org.example.effectivemobiletask.dto.response.Response;
import org.example.effectivemobiletask.dto.view.EmailDTO;
import org.example.effectivemobiletask.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAllByUserUsername(@AuthenticationPrincipal UserDetails userDetails) {
        List<EmailDTO> emails = emailService.getAllByUserUsername(userDetails.getUsername());

        return new Response(HttpStatus.OK.name(), emails);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createByUserUsername(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody EmailRequest request) {
        EmailDTO email = emailService.createByUserUsername(userDetails.getUsername(), request);

        return new Response(HttpStatus.CREATED.name(), email);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateByIdAndUserUsername(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody EmailRequest request) {
        EmailDTO email = emailService.updateByIdAndUserUsername(id, userDetails.getUsername(), request);

        return new Response(HttpStatus.OK.name(), email);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteByIdAndUserUsername(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        emailService.deleteByIdAndUserUsername(id, userDetails.getUsername());

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
