package com.mendax47.learn.module.auth.dtos.responses;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.mendax47.learn.module.user.User}
 */

public record LoginResponseDTO(
        String token,
        String username,
        String email,
        Set< String > roles
) implements Serializable {
}