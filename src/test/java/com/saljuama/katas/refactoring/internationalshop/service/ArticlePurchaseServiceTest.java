package com.saljuama.katas.refactoring.internationalshop.service;

import com.saljuama.katas.refactoring.internationalshop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticlePurchaseServiceTest {

  @Mock private ArticleRepository articleRepository;
  @Mock private ContractRepository contractRepository;
  @Mock private PurchaseRepository purchaseRepository;
  private ArticlePurchaseService service;

  @BeforeEach
  void setUp() {
    service = new ArticlePurchaseService(articleRepository, contractRepository, purchaseRepository);
  }

  @Test
  void onNewArticleSellsItToACustomer() {
    Region region = new Region(1L, "Valencia", "46001,46002");
    Article article = new Article("RockCD", Category.MUSIC, "Awesome mix", region);
    when(contractRepository.findByRegion(region)).thenReturn(Arrays.asList(
        new Contract(1L, "customer1", region, Category.MUSIC, 10, LocalDate.now().minusDays(1), null),
        new Contract(2L, "customer2", region, Category.MUSIC, 10, LocalDate.now().minusDays(1), null),
        new Contract(3L, "customer3", region, Category.MUSIC, 10, LocalDate.now().minusDays(1), null)
    ));
    when(articleRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0, Article.class).withId(1L));
    when(purchaseRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0, Purchase.class));

    Optional<Purchase> result = service.distributeNewArticle(article);

    result.ifPresentOrElse(
        purchase -> {
          assertEquals("RockCD", purchase.getArticle().getName());
          assertEquals(Category.MUSIC, purchase.getCategory());
          assertNotNull(purchase.getContract());
          assertNotNull(purchase.getPrice());
        },
        () -> fail("Should have sold it to a customer")
    );
  }

  @Test
  void onNewArticleWhenNoCustomersAvailableDoesNotCreatePurchaseAndDiscardTheArticle() {
    Region region = new Region(1L, "Valencia", "46001,46002");
    Article article = new Article("RockCD", Category.MUSIC, "Awesome mix", region);
    when(contractRepository.findByRegion(region)).thenReturn(Collections.emptyList());

    Optional<Purchase> result = service.distributeNewArticle(article);

    assertTrue(result.isEmpty());
    verify(articleRepository, never()).save(any());
    verify(purchaseRepository, never()).save(any());
  }

}
