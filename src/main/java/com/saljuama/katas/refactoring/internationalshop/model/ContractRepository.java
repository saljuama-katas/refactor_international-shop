package com.saljuama.katas.refactoring.internationalshop.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
  List<Contract> findByCustomerId(String customerId);

  List<Contract> findByRegion(Region region);
}
