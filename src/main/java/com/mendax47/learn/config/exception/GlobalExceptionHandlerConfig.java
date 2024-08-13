package com.mendax47.learn.config.exception;

import com.mendax47.learn.config.exception.dtos.response.ExceptionResponse;
import com.mendax47.learn.config.exception.enums.ErrorCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandlerConfig {
    @ExceptionHandler( BadCredentialsException.class )
    public ResponseEntity< ExceptionResponse > badCredentialExceptionHandler() {
        return ResponseEntity
                .status( HttpStatus.UNAUTHORIZED )
                .body(
                        ExceptionResponse
                                .builder()
                                .errorCode( ErrorCodes.BAD_CREDENTIALS.getCode() )
                                .errorDescription( ErrorCodes.BAD_CREDENTIALS.getDescription() )
                                .error( "Invalid Username or Password. Please try again." )
                                .build()
                );
    }

    @ExceptionHandler( UsernameNotFoundException.class )
    public ResponseEntity< ExceptionResponse > userNotFoundExceptionHandler() {
        return ResponseEntity
                .status( HttpStatus.NOT_FOUND )
                .body(
                        ExceptionResponse
                                .builder()
                                .errorCode( ErrorCodes.RESOURCE_NOT_FOUND.getCode() )
                                .errorDescription( ErrorCodes.RESOURCE_NOT_FOUND.getDescription() )
                                .error( "User not found." )
                                .build()
                );
    }

    @ExceptionHandler( AuthorizationDeniedException.class )
    public ResponseEntity< ExceptionResponse > authorizationDeniedExceptionHandler(
    ) {
        return ResponseEntity
                .status( HttpStatus.FORBIDDEN )
                .body(
                        ExceptionResponse
                                .builder()
                                .errorCode( ErrorCodes.AUTHORIZATION_DENIED.getCode() )
                                .errorDescription( ErrorCodes.AUTHORIZATION_DENIED.getDescription() )
                                .error( "This user is not authorized to perform this task." )
                                .build()
                );
    }
}