package com.mendax47.learn.module.role.dtos.responses;

import java.util.Set;

/**
 * Projection for {@link com.mendax47.learn.module.role.Role}
 */

public interface CustomRoleResponseDTO {
    Long getId();

    String getRoleType();

    Set< UserInfo > getUsers();

    /**
     * Projection for {@link com.mendax47.learn.module.user.User}
     */

    interface UserInfo {
        Long getId();

        String getFirstName();

        String getLastName();

        String getUsername();

        String getEmail();
    }
}