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
    repository.save(new Contract("customer1", savedRegion("Barcelona", "08001,08002"), Category.MUSIC, 5, LocalDate.now().minusDays(10), null));
    assertEquals(1L, repository.count());
  }

  @Test
  void findContractsByCustomer() {
    Region savedRegion = savedRegion("Barcelona", "08001,08002");
    repository.save(new Contract("customer1", savedRegion, Category.MUSIC, 5, LocalDate.now().minusDays(10), null));
    repository.save(new Contract("customer2", savedRegion, Category.MUSIC, 6, LocalDate.now().minusDays(12), null));
    repository.save(new Contract("customer1", savedRegion, Category.MUSIC, 7, LocalDate.now().minusDays(15), null));

    List<Contract> result = repository.findByCustomerId("customer1");

    assertEquals(2, result.size());
  }

  @Test
  void findByRegion() {
    Region barcelona = savedRegion("Barcelona", "08001,08002");
    Region valencia = savedRegion("Valencia", "46001,46002");
    repository.save(new Contract("customer1", barcelona, Category.MUSIC, 5, LocalDate.now().minusDays(10), null));
    repository.save(new Contract("customer1", valencia, Category.MUSIC, 7, LocalDate.now().minusDays(15), null));
    repository.save(new Contract("customer2", barcelona, Category.MUSIC, 6, LocalDate.now().minusDays(12), null));
    repository.save(new Contract("customer3", barcelona, Category.MUSIC, 6, LocalDate.now().minusDays(12), null));
    repository.save(new Contract("customer4", valencia, Category.MUSIC, 7, LocalDate.now().minusDays(15), null));

    assertEquals(3, repository.findByRegion(barcelona).size());
    assertEquals(2, repository.findByRegion(valencia).size());
  }

  private Region savedRegion(String city, String postalCodes) {
    return regionRepository.save(new Region(city, postalCodes));
  }
}
