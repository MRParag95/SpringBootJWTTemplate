package com.mendax47.learn.module.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
@EntityListeners( AuditingEntityListener.class )
public class BaseEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Column( nullable = false )
    private Boolean isActive = true;

    @Column( updatable = false )
    @CreatedDate
    private LocalDateTime createdAt;

    @Column( updatable = false )
    @CreatedBy
    private String createdBy;

    @Column( insertable = false )
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column( insertable = false )
    @LastModifiedBy
    private String updatedBy;
}