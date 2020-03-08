package com.saljuama.katas.refactoring.internationalshop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
@EqualsAndHashCode(callSuper = false)
public class Contract extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long customerId;
  @ManyToOne(fetch = FetchType.LAZY)
  private Region region;
  private Category category;
  private Integer weeklyLimit;
  private LocalDate startDate;
  private LocalDate endDate;
}
