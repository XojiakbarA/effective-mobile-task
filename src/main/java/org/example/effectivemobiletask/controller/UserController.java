package org.example.effectivemobiletask.controller;

import org.example.effectivemobiletask.dto.request.FilterRequest;
import org.example.effectivemobiletask.dto.response.Response;
import org.example.effectivemobiletask.dto.view.UserDTO;
import org.example.effectivemobiletask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/search")
    public Response search(@RequestBody List<FilterRequest> filters,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "id") String sortBy,
                           @RequestParam(defaultValue = "asc") String dir) {
        Page<UserDTO> users = userService.getAllByFilter(filters, page, size, sortBy, dir);

        return new Response(HttpStatus.OK.name(), users);
    }
}
