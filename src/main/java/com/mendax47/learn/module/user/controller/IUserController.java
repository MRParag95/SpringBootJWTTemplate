package com.mendax47.learn.module.user.controller;

import com.mendax47.learn.generics.controllers.ICRUDController;
import com.mendax47.learn.generics.dtos.responses.GenericResponseDTO;
import com.mendax47.learn.module.user.User;
import com.mendax47.learn.module.user.dtos.requests.UserRequestDTO;
import com.mendax47.learn.module.user.dtos.requests.UserRolesRequestDTO;
import com.mendax47.learn.module.user.dtos.responses.CustomUserResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IUserController extends ICRUDController< User, UserRequestDTO, GenericResponseDTO, CustomUserResponseDTO > {
    ResponseEntity< GenericResponseDTO > setUserRoles( UserRolesRequestDTO requestDTO );
}