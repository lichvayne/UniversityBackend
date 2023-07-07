package com.example.universitybackend.entity;

import com.example.universitybackend.record.RecordState;
import jakarta.persistence.*;
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
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AppEntity<ID extends Serializable> {

    @Column(updatable = false,name = "create_date")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "record_state")
    protected RecordState recordState = RecordState.ACTIVE;

}