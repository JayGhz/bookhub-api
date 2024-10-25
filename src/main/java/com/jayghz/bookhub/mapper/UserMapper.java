package com.jayghz.bookhub.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.jayghz.bookhub.dto.AuthResponseDTO;
import com.jayghz.bookhub.dto.LoginDTO;
import com.jayghz.bookhub.dto.UserProfileDTO;
import com.jayghz.bookhub.dto.UserRegisterDTO;
import com.jayghz.bookhub.model.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public User toUserEntity(UserRegisterDTO registerDTO) {
        return modelMapper.map(registerDTO, User.class);
    }

    public UserProfileDTO toUserProfileDTO(User user) {
        // Generar condcion si el usuario es un cliente o un autor
        UserProfileDTO userProfileDTO = modelMapper.map(user, UserProfileDTO.class);

        if (user.getCustomer() != null) {
            userProfileDTO.setFirstName(user.getCustomer().getFirstName());
            userProfileDTO.setLastName(user.getCustomer().getLastName());
            userProfileDTO.setShippingAddress(user.getCustomer().getShippingAddress());
        } else if (user.getAuthor() != null) {
            userProfileDTO.setFirstName(user.getAuthor().getFirstName());
            userProfileDTO.setLastName(user.getAuthor().getLastName());
            userProfileDTO.setBio(user.getAuthor().getBio());
        }

        return userProfileDTO;
    }

    // Convertir de LoginDTO a User (para autenticación)
    public User toUserEntity(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, User.class);
    }

    // Convertir de User a AuthResponseDTO
    public AuthResponseDTO toAuthResponseDTO(User user, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);

        // Obtener el primer nombre y apellido del usuario
        String firstName = (user.getCustomer() != null) ? user.getCustomer().getFirstName()
                : (user.getAuthor() != null) ? user.getAuthor().getFirstName()
                        : "Admin";
        String lastName = (user.getCustomer() != null) ? user.getCustomer().getLastName()
                : (user.getAuthor() != null) ? user.getAuthor().getLastName()
                        : "User";

        authResponseDTO.setFirstName(firstName);
        authResponseDTO.setLastName(lastName);

        authResponseDTO.setRole(user.getRole().getName().name());

        return authResponseDTO;
    }
}
