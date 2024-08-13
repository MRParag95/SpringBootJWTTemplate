package com.mendax47.learn.module.role.service;

import com.mendax47.learn.generics.dtos.responses.GenericResponseDTO;
import com.mendax47.learn.generics.services.ICRUDService;
import com.mendax47.learn.module.role.Role;
import com.mendax47.learn.module.role.dtos.requests.RoleRequestDTO;
import com.mendax47.learn.module.role.dtos.responses.CustomRoleResponseDTO;

public interface IRoleService extends ICRUDService< Role, RoleRequestDTO, GenericResponseDTO, CustomRoleResponseDTO > {
}