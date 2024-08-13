package com.mendax47.learn.module.auth.service;

import com.mendax47.learn.module.auth.dtos.requests.LoginRequestDTO;
import com.mendax47.learn.module.auth.dtos.responses.LoginResponseDTO;

public interface IAuthenticationService {
    LoginResponseDTO login( LoginRequestDTO requestDTO );
}