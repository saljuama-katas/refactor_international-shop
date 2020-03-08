package com.saljuama.katas.refactoring.internationalshop.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("integration-test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegionRepositoryTest {

  @Autowired
  private RegionRepository repository;

  @AfterEach
  void tearDown() {
    repository.deleteAll();
  }

  @Test
  void canSaveANewRegion() {
    Region newRegion = new Region();
    newRegion.setName("Barcelona");
    newRegion.setPostalCodes("08001,08002,08003,08004,08005,08006,08007,08008,08009,08010,08011,08012,08013,08014,08015");
    repository.save(newRegion);

    List<Region> regions = repository.findAll();
    assertEquals(1, regions.size());
  }
}