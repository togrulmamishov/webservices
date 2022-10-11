package com.azercell.webservices.controller;

import com.azercell.webservices.UserDao;
import com.azercell.webservices.entity.User;
import com.azercell.webservices.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserDao userDao;

    @GetMapping("/users")
    public List<User> retrieveAll() {
        return userDao.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable Integer id) {
        return userDao.findOne(id)
                .orElseThrow(() -> new UserNotFoundException("id = " + id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userDao.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
