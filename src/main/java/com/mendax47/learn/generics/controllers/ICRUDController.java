package com.mendax47.learn.generics.controllers;

import com.mendax47.learn.generics.dtos.responses.PageDataResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ICRUDController< E, RE, RES, CRES > {
    ResponseEntity< RES > create( RE requestDto );

    PageDataResponseDTO readAll( int pageNumber, int pageSize );

    ResponseEntity< CRES > readOne( Long id );

    ResponseEntity< RES > update( RE requestDto );

    ResponseEntity< RES > delete( Long id );
}