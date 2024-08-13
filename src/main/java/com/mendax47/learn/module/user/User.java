package com.mendax47.learn.module.user;

import com.mendax47.learn.module.base.BaseEntity;
import com.mendax47.learn.module.role.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@SQLRestriction( "is_active = TRUE" )
@SQLDelete( sql = "UPDATE _user SET is_active = FALSE WHERE id = ?" )
@Entity
@Table( name = "_user" )
public class User extends BaseEntity {
    @Column( nullable = false )
    private String firstName;

    @Column( nullable = false )
    private String lastName;

    @Column( nullable = false )
    private String username;

    @Column( nullable = false )
    private String email;

    @Column( nullable = false )
    private String password;

    @ManyToMany(
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH },
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "_user_roles",
            joinColumns = @JoinColumn( name = "user_id" ),
            inverseJoinColumns = @JoinColumn( name = "roles_id" )
    )
    private Set< Role > roles = new LinkedHashSet<>();
}