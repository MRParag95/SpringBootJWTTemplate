package com.mendax47.learn.module.role.controller;

import com.mendax47.learn.generics.controllers.ICRUDController;
import com.mendax47.learn.generics.dtos.responses.GenericResponseDTO;
import com.mendax47.learn.module.role.Role;
import com.mendax47.learn.module.role.dtos.requests.RoleRequestDTO;
import com.mendax47.learn.module.role.dtos.responses.CustomRoleResponseDTO;

public interface IRoleController extends ICRUDController< Role, RoleRequestDTO, GenericResponseDTO, CustomRoleResponseDTO > {
}