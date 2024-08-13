package com.mendax47.learn.audit;

import com.mendax47.learn.module.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuditAwareString implements AuditorAware< String > {
    @Override
    public Optional< String > getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (
                Objects.isNull( authentication )
                        || !authentication.isAuthenticated()
                        || authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.of( "Mendax47" );
        }

        CustomUserDetails userDetails = ( CustomUserDetails ) authentication.getPrincipal();

        return Optional.of( userDetails.getUsername() );
    }
}