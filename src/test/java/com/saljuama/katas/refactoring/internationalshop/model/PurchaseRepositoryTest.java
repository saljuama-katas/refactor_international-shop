package com.saljuama.katas.refactoring.internationalshop.model;

import com.saljuama.katas.refactoring.internationalshop.PersistenceIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PurchaseRepositoryTest extends PersistenceIntegrationTest {

  @Autowired private RegionRepository regionRepository;
  @Autowired private ContractRepository contractRepository;
  @Autowired private ArticleRepository articleRepository;
  @Autowired private PurchaseRepository repository;

  @AfterEach
  void tearDown() {
    repository.deleteAll();
    articleRepository.deleteAll();
    contractRepository.deleteAll();
    regionRepository.deleteAll();
  }

  @Test
  void canSaveANewPurchase() {
    assertEquals(0L, repository.count());

    Region barcelona = regionRepository.save(new Region("Barcelona", "08001,08002"));
    Article rockCD = articleRepository.save(new Article("Rock CD", Category.MUSIC, "\\m/", barcelona));
    Contract rockNRock = contractRepository.save(new Contract("customer1", barcelona, Category.MUSIC, 5, LocalDate.now().minusDays(10), null));

    repository.save(new Purchase(rockCD, rockNRock, Category.MUSIC, new BigDecimal("19.95")));

    assertEquals(1L, repository.count());
  }
}