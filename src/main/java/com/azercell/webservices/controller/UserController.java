package com.azercell.webservices.controller;

import com.azercell.webservices.entity.Post;
import com.azercell.webservices.entity.User;
import com.azercell.webservices.exception.UserNotFoundException;
import com.azercell.webservices.mapper.PostMapper;
import com.azercell.webservices.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final UserMapper userMapper;
    private final PostMapper postMapper;

    @GetMapping("/users")
    public List<User> retrieveAll() {
        return userMapper.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable Integer id) {

        User user = userMapper.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id = " + id));

        return user;
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrievePosts(@PathVariable Integer id) {
        User user = userMapper.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id = " + id));
        List<Post> posts = postMapper.getPostsByUserId(user.getId());
        return posts;
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer userId,
                           @RequestBody Post post) {
        userMapper.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("id = " + userId));

        post.setUserId(userId);
        postMapper.createPost(post);
        Integer insertedId = postMapper.getCurrentId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(insertedId)
                .toUri();

        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        userMapper.save(user);
        int insertId = userMapper.getInsertedId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(insertId)
                .toUri();

        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userMapper.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        userMapper.deleteById(id);
    }
}
