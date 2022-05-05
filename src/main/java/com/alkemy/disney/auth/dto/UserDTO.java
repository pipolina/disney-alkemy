package com.alkemy.disney.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    @Email(message = "The user must be an email")
    private String username;
    @Size(min=8)
    private String password;
}
