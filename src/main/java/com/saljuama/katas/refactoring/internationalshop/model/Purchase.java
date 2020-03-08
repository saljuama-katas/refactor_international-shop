package com.saljuama.katas.refactoring.internationalshop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "purchases")
@Data
@EqualsAndHashCode(callSuper = false)
public class Purchase extends AuditableEntity {
  @EmbeddedId
  private PurchaseId id;
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("articleId")
  private Article article;
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("contractId")
  private Contract contract;
  private Category category;
  private BigDecimal price;
}

@Embeddable
@Data
class PurchaseId implements Serializable {
  private Long articleId;
  private Long contractId;
}