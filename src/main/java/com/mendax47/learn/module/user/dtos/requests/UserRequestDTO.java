package com.mendax47.learn.module.user.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * DTO for {@link com.mendax47.learn.module.user.User}
 */

public record UserRequestDTO(
        Long id,

        @NotEmpty( message = "First name can't be null or empty." )
        String firstName,

        @NotEmpty( message = "Last name can't be null or empty." )
        String lastName,

        @NotEmpty( message = "Username can't be null or empty." )
        String username,

        @NotEmpty( message = "Email address can't be null or empty." )
        @Email( message = "Enter a valid email address." )
        String email,

        @NotEmpty( message = "Password can't be null or empty." )
        String password
) implements Serializable {
}