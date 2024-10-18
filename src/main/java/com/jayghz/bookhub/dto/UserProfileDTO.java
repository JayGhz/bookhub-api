package com.jayghz.bookhub.dto;

import com.jayghz.bookhub.model.enums.ERole;
import lombok.Data;

@Data
public class UserProfileDTO {
    private Integer id;
    private String email;
    private ERole role;
    private String firstName;
    private String lastName;

    private String shippingAddress;
    private String bio;
}
