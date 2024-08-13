package com.mendax47.learn.config.security;

import com.mendax47.learn.module.auth.CustomUserDetails;
import com.mendax47.learn.module.role.Role;
import com.mendax47.learn.module.user.User;
import com.mendax47.learn.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        User foundUser = userRepository.findByUsernameOrEmail( username.strip(), username.strip() );
        if ( Objects.isNull( foundUser ) ) {
            throw new UsernameNotFoundException( username );
        }

        Set< String > grantedAuthorities = foundUser
                .getRoles()
                .stream()
                .map( Role::getRoleType )
                .collect( Collectors.toSet() );

        return CustomUserDetails
                .builder()
                .id( foundUser.getId() )
                .username( foundUser.getUsername() )
                .password( foundUser.getPassword() )
                .email( foundUser.getEmail() )
                .roles( grantedAuthorities )
                .build();
    }
}