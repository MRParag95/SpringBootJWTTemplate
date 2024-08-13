package com.mendax47.learn.config.exception.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude( JsonInclude.Include.NON_NULL )
public class ExceptionResponse {
    private Integer errorCode;
    private String errorDescription;
    private String error;
    private Set< String > validationErrors;
    private Map< String, String > errors;
}