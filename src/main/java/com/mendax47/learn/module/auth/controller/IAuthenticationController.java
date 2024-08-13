package com.mendax47.learn.module.auth.controller;

import com.mendax47.learn.module.auth.dtos.requests.LoginRequestDTO;
import com.mendax47.learn.module.auth.dtos.responses.LoginResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationController {
    ResponseEntity< LoginResponseDTO > login( LoginRequestDTO requestDTO );
}