package com.saljuama.katas.refactoring.internationalshop.model;

import com.saljuama.katas.refactoring.internationalshop.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ArticleRepositoryTest extends IntegrationTest {

  @Autowired private RegionRepository regionRepository;
  @Autowired private ArticleRepository repository;

  @AfterEach
  void tearDown() {
    repository.deleteAll();
    regionRepository.deleteAll();
  }

  @Test
  void canSaveANewArticle() {
    assertEquals(0L, repository.count());

    Region barcelona = regionRepository.save(new Region("Barcelona", "08001,08002"));
    repository.save(new Article("Article", Category.MUSIC, "", barcelona));

    assertEquals(1L, repository.count());
  }
}
