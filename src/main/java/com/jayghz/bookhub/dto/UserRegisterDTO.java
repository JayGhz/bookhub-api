package com.jayghz.bookhub.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegisterDTO {
    
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    // Campo si el usuario es un cliente
    private String shippingAddress;

    // Campo si el usuario es un autor
    private String bio;
}
