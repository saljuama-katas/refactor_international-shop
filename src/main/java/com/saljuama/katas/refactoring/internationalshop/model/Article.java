package com.saljuama.katas.refactoring.internationalshop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "articles")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Article extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Category category;
  private String description;
  @ManyToOne(fetch = FetchType.LAZY)
  private Region region;

  public Article(String name, Category category, String description, Region region) {
    this.name = name;
    this.category = category;
    this.description = description;
    this.region = region;
  }
}
