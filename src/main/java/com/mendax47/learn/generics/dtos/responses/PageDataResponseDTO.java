package com.mendax47.learn.generics.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDataResponseDTO {
    int totalPages;
    int currentPage;
    long totalElements;
    private Collection< ? > model;
}