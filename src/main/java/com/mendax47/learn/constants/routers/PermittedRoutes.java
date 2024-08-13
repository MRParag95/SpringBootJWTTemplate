package com.mendax47.learn.constants.routers;

public final class PermittedRoutes {
    public static final String[] ROUTES = new String[] {
            "/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            UserRoutes.USER_ROUTE + "/" + UserRoutes.USER_REGISTRATION,
            AuthenticationRoutes.AUTHENTICATION + "/" + AuthenticationRoutes.AUTHENTICATION_LOGIN,
    };
}