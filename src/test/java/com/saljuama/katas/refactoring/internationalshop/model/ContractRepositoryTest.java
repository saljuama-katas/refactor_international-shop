package com.saljuama.katas.refactoring.internationalshop.model;

import com.saljuama.katas.refactoring.internationalshop.PersistenceIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ContractRepositoryTest extends PersistenceIntegrationTest {

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
    repository.save(new Contract("customer1", savedRegion(), Category.MUSIC, 5, LocalDate.now().minusDays(10), null));
    assertEquals(1L, repository.count());
  }

  @Test
  void findContractsByCustomer() {
    Region savedRegion = savedRegion();
    repository.save(new Contract("customer1", savedRegion, Category.MUSIC, 5, LocalDate.now().minusDays(10), null));
    repository.save(new Contract("customer2", savedRegion, Category.MUSIC, 6, LocalDate.now().minusDays(12), null));
    repository.save(new Contract("customer1", savedRegion, Category.MUSIC, 7, LocalDate.now().minusDays(15), null));

    List<Contract> result = repository.findByCustomerId("customer1");

    assertEquals(2, result.size());
  }

  private Region savedRegion() {
    return regionRepository.save(new Region("Barcelona", "08001,08002"));
  }
}
