package com.jayghz.bookhub.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.jayghz.bookhub.model.entity.User;
import com.jayghz.bookhub.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController  {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
