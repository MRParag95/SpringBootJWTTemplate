package com.mendax47.learn.config.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum ErrorCodes {
    RESOURCE_NOT_FOUND( 404, "Resource not found.", NOT_FOUND ),
    AUTHORIZATION_DENIED( 401, "Authorization required.", FORBIDDEN ),
    BAD_CREDENTIALS( 304, "Login and / or Password is incorrect.", FORBIDDEN ),
    ;

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;
}