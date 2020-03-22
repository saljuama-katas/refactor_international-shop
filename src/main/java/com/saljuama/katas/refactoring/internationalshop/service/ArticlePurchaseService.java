package com.saljuama.katas.refactoring.internationalshop.service;

import com.saljuama.katas.refactoring.internationalshop.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ArticlePurchaseService {

  private final ArticleRepository articleRepository;
  private final ContractRepository contractRepository;
  private final PurchaseRepository purchaseRepository;

  public Optional<Purchase> distributeNewArticle(Article article) {
    Article savedArticle = articleRepository.save(article);
    Optional<Purchase> purchase = contractRepository
        .findByRegion(savedArticle.getRegion())
        .stream()
        .findFirst()
        .map(contract -> new Purchase(savedArticle, contract, savedArticle.getCategory(), new BigDecimal("1.50")));
    purchase.ifPresent(p -> purchaseRepository.save(p));
    return purchase;
  }
}
