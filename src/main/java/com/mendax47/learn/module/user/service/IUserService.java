package com.mendax47.learn.module.user.service;

import com.mendax47.learn.generics.dtos.responses.GenericResponseDTO;
import com.mendax47.learn.generics.services.ICRUDService;
import com.mendax47.learn.module.user.User;
import com.mendax47.learn.module.user.dtos.requests.UserRequestDTO;
import com.mendax47.learn.module.user.dtos.requests.UserRolesRequestDTO;
import com.mendax47.learn.module.user.dtos.responses.CustomUserResponseDTO;

public interface IUserService extends ICRUDService< User, UserRequestDTO, GenericResponseDTO, CustomUserResponseDTO > {
    GenericResponseDTO setUserRoles( UserRolesRequestDTO requestDTO );
}