package com.example.universitybackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public abstract class AppEntity<ID extends Serializable> {

    @Column(updatable = false,name = "create_date")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createDate;

    @Column(updatable = true,name = "updated_date")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedDate;

    @Column(updatable = true, name = "deleted")
    protected boolean deleted = false;
}