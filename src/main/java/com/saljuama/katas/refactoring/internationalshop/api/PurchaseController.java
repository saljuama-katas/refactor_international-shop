package com.saljuama.katas.refactoring.internationalshop.api;

import com.saljuama.katas.refactoring.internationalshop.model.Purchase;
import com.saljuama.katas.refactoring.internationalshop.model.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@AllArgsConstructor
public class PurchaseController {

  private final PurchaseRepository repository;

  @GetMapping("/{customerId}")
  public ResponseEntity<List<Purchase>> getPurchasesByCustomer(@PathVariable("customerId") String customerId) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(repository.findByContractCustomerId(customerId));
  }
}
