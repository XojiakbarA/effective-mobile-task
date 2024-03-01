package org.example.effectivemobiletask.controller;

import org.example.effectivemobiletask.dto.request.PhoneRequest;
import org.example.effectivemobiletask.dto.response.Response;
import org.example.effectivemobiletask.dto.view.PhoneDTO;
import org.example.effectivemobiletask.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phones")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAllByUserUsername(@AuthenticationPrincipal UserDetails userDetails) {
        List<PhoneDTO> phones = phoneService.getAllByUserUsername(userDetails.getUsername());

        return new Response(HttpStatus.OK.name(), phones);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createByUserUsername(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody PhoneRequest request) {
        PhoneDTO phone = phoneService.createByUserUsername(userDetails.getUsername(), request);

        return new Response(HttpStatus.CREATED.name(), phone);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateByIdAndUserUsername(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody PhoneRequest request) {
        PhoneDTO phone = phoneService.updateByIdAndUserUsername(id, userDetails.getUsername(), request);

        return new Response(HttpStatus.OK.name(), phone);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteByIdAndUserUsername(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        phoneService.deleteByIdAndUserUsername(id, userDetails.getUsername());

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
