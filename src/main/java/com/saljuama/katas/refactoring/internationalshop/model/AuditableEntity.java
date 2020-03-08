package com.saljuama.katas.refactoring.internationalshop.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity {
  @CreatedDate
  protected LocalDateTime createdDate;
  @LastModifiedDate
  protected LocalDateTime lastModifiedDate;
}
