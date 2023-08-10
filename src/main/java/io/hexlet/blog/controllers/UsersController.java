package io.hexlet.blog.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hexlet.blog.dtos.UserDTO;
import io.hexlet.blog.repositories.UserRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UsersController {
    @Autowired
    private final UserRepository repository;

    @Autowired
    private ModelMapper mm;

    @GetMapping("/users")
    ResponseEntity<List<UserDTO>> index() {
        var users = repository.findAll();
        var result = users.stream()
                .map((user) -> mm.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(users.size()))
                .body(result);
    }
}