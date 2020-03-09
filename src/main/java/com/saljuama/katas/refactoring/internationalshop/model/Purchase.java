package com.saljuama.katas.refactoring.internationalshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "purchases")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Purchase extends AuditableEntity {
  @EmbeddedId
  private PurchaseId id = new PurchaseId();
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("articleId")
  private Article article;
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("contractId")
  private Contract contract;
  private Category category;
  private BigDecimal price;

  public Purchase(Article article, Contract contract, Category category, BigDecimal price) {
    this.id = new PurchaseId(article.getId(), contract.getId());
    this.article = article;
    this.contract = contract;
    this.category = category;
    this.price = price;
  }
}

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
class PurchaseId implements Serializable {
  private Long articleId;
  private Long contractId;
}