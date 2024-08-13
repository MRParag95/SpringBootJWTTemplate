package com.mendax47.learn.generics.services;

import com.mendax47.learn.generics.dtos.responses.PageDataResponseDTO;

public interface ICRUDService< E, RE, RES, CRES > {
    RES create( RE requestDto );

    PageDataResponseDTO readAll( int pageNumber, int pageSize );

    CRES readOne( Long id );

    RES update( RE requestDto );

    RES delete( Long id );
}