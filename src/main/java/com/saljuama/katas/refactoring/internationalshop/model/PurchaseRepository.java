package com.saljuama.katas.refactoring.internationalshop.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, PurchaseId> {
  List<Purchase> findByContractCustomerId(String id);
}
