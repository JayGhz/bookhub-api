package com.jayghz.bookhub.service;

import com.jayghz.bookhub.dto.AuthResponseDTO;
import com.jayghz.bookhub.dto.LoginDTO;
import com.jayghz.bookhub.dto.UserProfileDTO;
import com.jayghz.bookhub.dto.UserRegisterDTO;

public interface UserService {
    // Registro de un cliente
    UserProfileDTO registerCustomer(UserRegisterDTO userRegisterDTO);

    // Registro de un autor
    UserProfileDTO registerAuthor(UserRegisterDTO userRegisterDTO);

    // Autenticar un usuario
    AuthResponseDTO login(LoginDTO loginDTO);

    // Actualizar perfil de un usuario
    UserProfileDTO updateUserProfile(Integer id, UserProfileDTO userProfileDTO);

    UserProfileDTO getUserProfileById(Integer id);
}
