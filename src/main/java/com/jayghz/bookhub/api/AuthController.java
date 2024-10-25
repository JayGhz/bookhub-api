package com.jayghz.bookhub.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.jayghz.bookhub.dto.AuthResponseDTO;
import com.jayghz.bookhub.dto.LoginDTO;
import com.jayghz.bookhub.dto.UserProfileDTO;
import com.jayghz.bookhub.dto.UserRegisterDTO;
import com.jayghz.bookhub.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController  {
    private final UserService userService;

    // Registrar un cliente
    @PostMapping("/register/customer")
    public ResponseEntity<UserProfileDTO> registerCustomer(@Valid @RequestBody UserRegisterDTO user) {
        UserProfileDTO newUser = userService.registerCustomer(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Registrar un autor
    @PostMapping("/register/author")
    public ResponseEntity<UserProfileDTO> registerAuthor(@Valid @RequestBody UserRegisterDTO user) {
        UserProfileDTO newUser = userService.registerAuthor(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    
    // Login
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO user) {
        AuthResponseDTO authResponse = userService.login(user);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
