package com.mendax47.learn.module.auth.controller;

import com.mendax47.learn.constants.routers.AuthenticationRoutes;
import com.mendax47.learn.module.auth.dtos.requests.LoginRequestDTO;
import com.mendax47.learn.module.auth.dtos.responses.LoginResponseDTO;
import com.mendax47.learn.module.auth.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping( AuthenticationRoutes.AUTHENTICATION )
public class AuthenticationController implements IAuthenticationController {
    private final IAuthenticationService authenticationService;

    @PostMapping( AuthenticationRoutes.AUTHENTICATION_LOGIN )
    @Override
    public ResponseEntity< LoginResponseDTO > login( @Validated @RequestBody LoginRequestDTO requestDTO ) {
        return ResponseEntity.ok( authenticationService.login( requestDTO ) );
    }
}