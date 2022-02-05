package com.example.timeleaf.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //?
@EntityListeners(AuditingEntityListener.class) //?
public abstract class BaseTimeEntity { //abstract의 역할

    @CreatedDate //?
    private LocalDateTime createDate;

    @LastModifiedDate//?
    private LocalDateTime modifiedDate;

}
