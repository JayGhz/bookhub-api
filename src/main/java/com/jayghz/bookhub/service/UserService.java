package com.jayghz.bookhub.service;

import com.jayghz.bookhub.dto.UserProfileDTO;
import com.jayghz.bookhub.dto.UserRegisterDTO;

public interface UserService {
    // Registro de un cliente
    UserProfileDTO registerCustomer(UserRegisterDTO userRegisterDTO);

    // Registro de un autor
    UserProfileDTO registerAuthor(UserRegisterDTO userRegisterDTO);

    // Actualizar perfil de un usuario
    UserProfileDTO updateUserProfile(Integer id, UserProfileDTO userProfileDTO);

    UserProfileDTO getUserProfileById(Integer id);
}
