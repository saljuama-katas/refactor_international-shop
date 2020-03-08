package com.saljuama.katas.refactoring.internationalshop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "articles")
@Data
@EqualsAndHashCode(callSuper = false)
public class Article extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Category category;
  private String description;
  @ManyToOne(fetch = FetchType.LAZY)
  private Region region;
}
