package com.saljuama.katas.refactoring.internationalshop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
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

  public Contract(Long customerId, Region region, Category category, Integer weeklyLimit, LocalDate startDate, LocalDate endDate) {
    this.customerId = customerId;
    this.region = region;
    this.category = category;
    this.weeklyLimit = weeklyLimit;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
