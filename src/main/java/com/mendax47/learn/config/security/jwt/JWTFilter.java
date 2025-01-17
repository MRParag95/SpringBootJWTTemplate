package com.mendax47.learn.config.security.jwt;

import com.mendax47.learn.config.security.CustomUserDetailsService;
import com.mendax47.learn.constants.routers.AuthenticationRoutes;
import com.mendax47.learn.constants.routers.UserRoutes;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader( "Authorization" );

        final String jwt;
        final String username;

        if ( Objects.isNull( authorizationHeader ) || !authorizationHeader.startsWith( "Bearer " ) ) {
            filterChain.doFilter( request, response );
            return;
        }

        jwt = authorizationHeader.substring( 7 );
        username = jwtService.extractUsername( jwt );

        if ( Objects.nonNull( username ) && Objects.isNull( SecurityContextHolder.getContext().getAuthentication() ) ) {
            UserDetails user = customUserDetailsService.loadUserByUsername( username );

            if ( jwtService.isTokenValid( jwt, user ) ) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        username,
                        user.getPassword(),
                        user.getAuthorities()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails( request )
                );
                SecurityContextHolder.getContext().setAuthentication( authenticationToken );
            }
        }
        filterChain.doFilter( request, response );
    }

    @Override
    protected boolean shouldNotFilter( @NotNull HttpServletRequest request ) throws ServletException {
        return (
                request.getRequestURI().contains( UserRoutes.USER_ROUTE + "/" + UserRoutes.USER_REGISTRATION )
                        || request.getRequestURI().contains(
                        AuthenticationRoutes.AUTHENTICATION + "/" + AuthenticationRoutes.AUTHENTICATION_LOGIN
                )
        );
    }
}