package com.mendax47.learn;

import com.mendax47.learn.module.role.Role;
import com.mendax47.learn.module.role.repository.RoleRepository;
import com.mendax47.learn.module.user.User;
import com.mendax47.learn.module.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing( auditorAwareRef = "auditAwareString" )
@EnableAsync
public class LearnApplication {
    public static void main( String[] args ) {
        SpringApplication.run( LearnApplication.class, args );
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            User foundSuperAdminUser = userRepository.findByUsernameOrEmail( "SuperAdmin", "SuperAdmin" );
            Role foundSuperAdminRole = roleRepository.findByRoleType( "SuperAdmin" );

            Set< Role > roles = new HashSet<>();

            if ( Objects.isNull( foundSuperAdminUser ) && Objects.isNull( foundSuperAdminRole ) ) {
                Role superAdminRole = new Role( "SuperAdmin" );
                roles.add( superAdminRole );

                User user = new User(
                        "Super",
                        "Admin",
                        "SuperAdmin",
                        "superadmin@technonext.com",
                        passwordEncoder.encode( "SuperAdmin" ),
                        roles
                );

                userRepository.save( user );
            }
        };
    }
}