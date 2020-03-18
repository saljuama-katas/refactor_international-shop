package com.saljuama.katas.refactoring.internationalshop.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "regions")
@Data
@With
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Region extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String postalCodes;

  public Region(String name, String postalCodes) {
    this.name = name;
    this.postalCodes = postalCodes;
  }
}
