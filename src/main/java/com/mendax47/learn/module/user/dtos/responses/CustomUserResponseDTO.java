package com.mendax47.learn.module.user.dtos.responses;

import java.util.Set;

/**
 * Projection for {@link com.mendax47.learn.module.user.User}
 */

public interface CustomUserResponseDTO {
    Long getId();

    String getFirstName();

    String getLastName();

    String getUsername();

    String getEmail();

    Set< RoleInfo > getRoles();

    /**
     * Projection for {@link com.mendax47.learn.module.role.Role}
     */

    interface RoleInfo {
        Long getId();

        String getRoleType();
    }
}