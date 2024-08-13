package com.mendax47.learn.module.user.controller;

import com.mendax47.learn.constants.routers.UserRoutes;
import com.mendax47.learn.generics.dtos.responses.GenericResponseDTO;
import com.mendax47.learn.generics.dtos.responses.PageDataResponseDTO;
import com.mendax47.learn.module.user.dtos.requests.UserRequestDTO;
import com.mendax47.learn.module.user.dtos.requests.UserRolesRequestDTO;
import com.mendax47.learn.module.user.dtos.responses.CustomUserResponseDTO;
import com.mendax47.learn.module.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping( UserRoutes.USER_ROUTE )
public class UserController implements IUserController {
    private final IUserService userService;

    @PostMapping( UserRoutes.USER_REGISTRATION )
    @Override
    public ResponseEntity< GenericResponseDTO > create( @Validated UserRequestDTO requestDto ) {
        return new ResponseEntity<>( userService.create( requestDto ), HttpStatus.CREATED );
    }

    @GetMapping
    @Override
    public PageDataResponseDTO readAll(
            @RequestParam( "pageNumber" ) int pageNumber,
            @RequestParam( "pageSize" ) int pageSize
    ) {
        return userService.readAll( pageNumber, pageSize );
    }

    @GetMapping( "{id}" )
    @Override
    public ResponseEntity< CustomUserResponseDTO > readOne( @PathVariable( "id" ) Long id ) {
        return ResponseEntity
                .ok()
                .body( userService.readOne( id ) );
    }

    @PutMapping
    @Override
    public ResponseEntity< GenericResponseDTO > update( @Validated UserRequestDTO requestDto ) {
        return ResponseEntity
                .ok()
                .body( userService.update( requestDto ) );
    }

    @PreAuthorize( "hasAnyRole('Admin')" )
    @DeleteMapping( "{id}" )
    @Override
    public ResponseEntity< GenericResponseDTO > delete( @PathVariable( "id" ) Long id ) {
        return ResponseEntity
                .ok()
                .body( userService.delete( id ) );
    }

    @PostMapping( "change-roles" )
    @Override
    public ResponseEntity< GenericResponseDTO > setUserRoles( @RequestBody UserRolesRequestDTO requestDTO ) {
        return ResponseEntity
                .ok()
                .body( userService.setUserRoles( requestDTO ) );
    }
}