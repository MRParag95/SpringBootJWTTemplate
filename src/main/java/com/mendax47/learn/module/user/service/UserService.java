package com.mendax47.learn.module.user.service;

import com.mendax47.learn.generics.dtos.responses.GenericResponseDTO;
import com.mendax47.learn.generics.dtos.responses.PageDataResponseDTO;
import com.mendax47.learn.module.role.Role;
import com.mendax47.learn.module.role.repository.RoleRepository;
import com.mendax47.learn.module.user.User;
import com.mendax47.learn.module.user.dtos.requests.UserRequestDTO;
import com.mendax47.learn.module.user.dtos.requests.UserRolesRequestDTO;
import com.mendax47.learn.module.user.dtos.responses.CustomUserResponseDTO;
import com.mendax47.learn.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public GenericResponseDTO create( UserRequestDTO requestDto ) {
        UserRequestDTO cleanedUserRequestDTOFields = cleanDTOFields( requestDto );
        validate( cleanedUserRequestDTOFields, null );
        userRepository.save( toEntityConverter( cleanedUserRequestDTOFields, null ) );

        return GenericResponseDTO
                .builder()
                .statusCode( HttpStatus.CREATED.toString() )
                .statusMessage( "User Created Successfully." )
                .build();
    }

    @Override
    public PageDataResponseDTO readAll( int pageNumber, int pageSize ) {
        Pageable pageable = PageRequest.of( pageNumber - 1, pageSize );
        Page< CustomUserResponseDTO > allUsers = userRepository.findAllUsers( pageable );

        return PageDataResponseDTO
                .builder()
                .model( allUsers.getContent() )
                .totalElements( allUsers.getTotalElements() )
                .totalPages( allUsers.getTotalPages() )
                .currentPage( allUsers.getNumber() + 1 )
                .build();
    }

    @Override
    public CustomUserResponseDTO readOne( Long id ) {
        CustomUserResponseDTO singleUserById = userRepository.findUserById( id );
        if ( Objects.isNull( singleUserById ) ) {
            throw new RuntimeException( "User with id " + id + " not found." );
        }
        return singleUserById;
    }

    @Override
    public GenericResponseDTO update( UserRequestDTO requestDto ) {
        UserRequestDTO cleanedUserRequestDTOFields = cleanDTOFields( requestDto );
        User foundUser = userRepository
                .findById( cleanedUserRequestDTOFields.id() )
                .orElseThrow( () -> new RuntimeException( "User with id " + requestDto.id() + " not found." ) );
        validate( cleanedUserRequestDTOFields, foundUser );
        userRepository.save( toEntityConverter( cleanedUserRequestDTOFields, foundUser ) );

        return GenericResponseDTO
                .builder()
                .statusCode( HttpStatus.OK.toString() )
                .statusMessage( "User Updated Successfully." )
                .build();
    }

    @Override
    public GenericResponseDTO delete( Long id ) {
        User foundUser = userRepository
                .findById( id )
                .orElseThrow( () -> new RuntimeException( "User Not Found." ) );
        deleteValidator( foundUser );
        userRepository.delete( foundUser );

        return GenericResponseDTO
                .builder()
                .statusCode( HttpStatus.OK.toString() )
                .statusMessage( "User Deleted Successfully." )
                .build();
    }

    @Override
    public GenericResponseDTO setUserRoles( UserRolesRequestDTO requestDTO ) {
        User foundUser = userRepository.findUserByUserId( requestDTO.userId() );

        if ( Objects.isNull( foundUser ) ) {
            throw new RuntimeException( "User with id " + requestDTO.userId() + " not found." );
        }

        Set< Role > foundRoles = roleRepository.findAllByIdIn( requestDTO.roleIds() );
        foundUser.getRoles().addAll( foundRoles );

        userRepository.save( foundUser );

        return GenericResponseDTO
                .builder()
                .statusCode( HttpStatus.OK.toString() )
                .statusMessage( "User's Role Updated Successfully." )
                .build();
    }

    private UserRequestDTO cleanDTOFields( UserRequestDTO requestDto ) {
        return new UserRequestDTO(
                requestDto.id(),
                requestDto.firstName().strip(),
                requestDto.lastName().strip(),
                requestDto.username().strip(),
                requestDto.email().strip(),
                passwordEncoder.encode( requestDto.password() )
        );
    }

    private void validate( UserRequestDTO requestDto, User user ) {
        if ( Objects.isNull( user ) ) {
            User foundUser = userRepository.findByUsernameOrEmail( requestDto.username(), requestDto.email() );

            if ( Objects.nonNull( foundUser ) ) {
                throw new RuntimeException( "Email address or Username is already in use." );
            }
        } else {
            if ( ( !Objects.equals( requestDto.username(), user.getUsername() ) && userRepository.existsByUsername( requestDto.username() ) )
                    || ( !Objects.equals( requestDto.email(), user.getEmail() ) && userRepository.existsByEmail( requestDto.email() ) )
            ) {
                throw new RuntimeException( "Email address or Username is already in use." );
            }
        }
    }

    private User toEntityConverter( UserRequestDTO requestDto, User user ) {
        if ( Objects.isNull( user ) ) {
            user = new User();
        }

        user.setId( requestDto.id() );
        user.setFirstName( requestDto.firstName() );
        user.setLastName( requestDto.lastName() );
        user.setUsername( requestDto.username() );
        user.setEmail( requestDto.email() );
        user.setPassword( requestDto.password() );

        return user;
    }

    private void deleteValidator( User user ) {
    }
}