package com.saljuama.katas.refactoring.internationalshop.model;

import com.saljuama.katas.refactoring.internationalshop.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ContractRepositoryTest extends IntegrationTest {

  @Autowired private RegionRepository regionRepository;
  @Autowired private ContractRepository repository;

  @AfterEach
  void tearDown() {
    repository.deleteAll();
    regionRepository.deleteAll();
  }

  @Test
  void canSaveANewContract() {
    assertEquals(0L, repository.count());

    Region barcelona = regionRepository.save(new Region("Barcelona", "08001,08002"));
    repository.save(new Contract(100L, barcelona, Category.MUSIC, 5, LocalDate.now().minusDays(10), null));

    assertEquals(1L, repository.count());
  }
}