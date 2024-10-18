package com.jayghz.bookhub.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.jayghz.bookhub.dto.UserProfileDTO;
import com.jayghz.bookhub.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-profile")
public class UserProfileController {
    private final UserService userService;

    // Actualizar perfil de usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable Integer id, @RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO updatedUserProfile = userService.updateUserProfile(id, userProfileDTO);
        return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
    }

    // Obtener perfil de usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getUserProfileById(@PathVariable Integer id) {
        UserProfileDTO userProfile = userService.getUserProfileById(id);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
}
