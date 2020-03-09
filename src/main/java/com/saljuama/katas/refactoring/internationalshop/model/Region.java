package com.saljuama.katas.refactoring.internationalshop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "regions")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
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
