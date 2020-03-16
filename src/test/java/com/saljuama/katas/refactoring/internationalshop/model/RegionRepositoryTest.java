package com.saljuama.katas.refactoring.internationalshop.model;

import com.saljuama.katas.refactoring.internationalshop.PersistenceIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RegionRepositoryTest extends PersistenceIntegrationTest {

  @Autowired
  private RegionRepository repository;

  @AfterEach
  void tearDown() {
    repository.deleteAll();
  }

  @Test
  void canSaveANewRegion() {
    assertEquals(0L, repository.count());

    Region newRegion = new Region("Barcelona", "08001,08002,08003,08004,08005");
    repository.save(newRegion);

    assertEquals(1L, repository.count());
  }

}