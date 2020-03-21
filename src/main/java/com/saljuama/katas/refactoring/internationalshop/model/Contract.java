package com.saljuama.katas.refactoring.internationalshop.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
@With
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Contract extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String customerId;
  @ManyToOne(fetch = FetchType.LAZY)
  private Region region;
  private Category category;
  private Integer weeklyLimit;
  private LocalDate startDate;
  private LocalDate endDate;

  public Contract(String customerId, Region region, Category category, Integer weeklyLimit, LocalDate startDate, LocalDate endDate) {
    this.customerId = customerId;
    this.region = region;
    this.category = category;
    this.weeklyLimit = weeklyLimit;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
