package com.mendax47.learn.module.role.service;

import com.mendax47.learn.generics.dtos.responses.GenericResponseDTO;
import com.mendax47.learn.generics.dtos.responses.PageDataResponseDTO;
import com.mendax47.learn.module.role.Role;
import com.mendax47.learn.module.role.dtos.requests.RoleRequestDTO;
import com.mendax47.learn.module.role.dtos.responses.CustomRoleResponseDTO;
import com.mendax47.learn.module.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Override
    public GenericResponseDTO create( RoleRequestDTO requestDto ) {
        RoleRequestDTO cleanedRoleRequestDTOFields = cleanDTOFields( requestDto );
        validate( cleanedRoleRequestDTOFields, null );
        roleRepository.save( toEntityConverter( cleanedRoleRequestDTOFields, null ) );

        return GenericResponseDTO
                .builder()
                .statusCode( HttpStatus.CREATED.toString() )
                .statusMessage( "Role Created Successfully." )
                .build();
    }

    @Override
    public PageDataResponseDTO readAll( int pageNumber, int pageSize ) {
        Pageable pageable = PageRequest.of( pageNumber - 1, pageSize );
        Page< CustomRoleResponseDTO > roles = roleRepository.findAllRoles( pageable );

        return PageDataResponseDTO
                .builder()
                .model( roles.getContent() )
                .totalElements( roles.getTotalElements() )
                .totalPages( roles.getTotalPages() )
                .currentPage( roles.getNumber() + 1 )
                .build();
    }

    @Override
    public CustomRoleResponseDTO readOne( Long id ) {
        CustomRoleResponseDTO singleRoleById = roleRepository.findRoleById( id );
        if ( Objects.isNull( singleRoleById ) ) {
            throw new RuntimeException( "Role with id " + id + " not found." );
        }
        return singleRoleById;
    }

    @Override
    public GenericResponseDTO update( RoleRequestDTO requestDto ) {
        RoleRequestDTO cleanedRoleRequestDTOFields = cleanDTOFields( requestDto );
        Role foundRole = roleRepository
                .findById( requestDto.id() )
                .orElseThrow( () -> new RuntimeException( "Role with id " + requestDto.id() + " not found." ) );
        validate( cleanedRoleRequestDTOFields, foundRole );
        roleRepository.save( toEntityConverter( cleanedRoleRequestDTOFields, foundRole ) );

        return GenericResponseDTO
                .builder()
                .statusCode( HttpStatus.OK.toString() )
                .statusMessage( "Role Updated Successfully." )
                .build();
    }

    @Override
    public GenericResponseDTO delete( Long id ) {
        Role foundRole = roleRepository
                .findById( id )
                .orElseThrow( () -> new RuntimeException( "Role with id " + id + " not found." ) );
        deleteValidator( foundRole );
        roleRepository.delete( foundRole );

        return GenericResponseDTO
                .builder()
                .statusCode( HttpStatus.OK.toString() )
                .statusMessage( "Role Deleted Successfully." )
                .build();
    }

    private RoleRequestDTO cleanDTOFields( RoleRequestDTO requestDto ) {
        return new RoleRequestDTO(
                requestDto.id(),
                requestDto.roleType()
        );
    }

    private void validate( RoleRequestDTO requestDto, Role role ) {
        if ( Objects.isNull( role ) ) {
            Role foundRole = roleRepository.findByRoleType( requestDto.roleType() );

            if ( Objects.nonNull( foundRole ) ) {
                throw new RuntimeException( "Role type already exists." );
            }
        } else {
            if ( ( !( Objects.equals( requestDto.roleType(), role.getRoleType() ) ) && roleRepository.existsByRoleType( requestDto.roleType() ) ) ) {
                throw new RuntimeException( "Role type already exists." );
            }
        }
    }

    private Role toEntityConverter( RoleRequestDTO requestDto, Role role ) {
        if ( Objects.isNull( role ) ) {
            role = new Role();
        }

        role.setId( requestDto.id() );
        role.setRoleType( requestDto.roleType() );

        return role;
    }

    private void deleteValidator( Role role ) {
    }
}