package com.mendax47.learn.module.role.controller;

import com.mendax47.learn.constants.routers.RoleRoutes;
import com.mendax47.learn.generics.dtos.responses.GenericResponseDTO;
import com.mendax47.learn.generics.dtos.responses.PageDataResponseDTO;
import com.mendax47.learn.module.role.dtos.requests.RoleRequestDTO;
import com.mendax47.learn.module.role.dtos.responses.CustomRoleResponseDTO;
import com.mendax47.learn.module.role.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping( RoleRoutes.ROLE_ROUTE )
public class RoleController implements IRoleController {
    private final IRoleService roleService;

    @PostMapping()
    @Override
    public ResponseEntity< GenericResponseDTO > create( @Validated RoleRequestDTO requestDto ) {
        return new ResponseEntity<>( roleService.create( requestDto ), HttpStatus.CREATED );
    }

    @GetMapping()
    @Override
    public PageDataResponseDTO readAll(
            @RequestParam( "pageNumber" ) int pageNumber,
            @RequestParam( "pageSize" ) int pageSize
    ) {
        return roleService.readAll( pageNumber, pageSize );
    }

    @GetMapping( "{id}" )
    @Override
    public ResponseEntity< CustomRoleResponseDTO > readOne( @PathVariable( "id" ) Long id ) {
        return ResponseEntity
                .ok()
                .body( roleService.readOne( id ) );
    }

    @PutMapping
    @Override
    public ResponseEntity< GenericResponseDTO > update( @Validated RoleRequestDTO requestDto ) {
        return ResponseEntity
                .ok()
                .body( roleService.update( requestDto ) );
    }

    @DeleteMapping( "{id}" )
    @Override
    public ResponseEntity< GenericResponseDTO > delete( @PathVariable( "id" ) Long id ) {
        return ResponseEntity
                .ok()
                .body( roleService.delete( id ) );
    }
}
