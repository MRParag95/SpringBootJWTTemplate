package com.mendax47.learn.module.role.dtos.requests;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.mendax47.learn.module.role.Role}
 */

public record RoleRequestDTO(
        Long id,
        @NotNull String roleType
) implements Serializable {
}