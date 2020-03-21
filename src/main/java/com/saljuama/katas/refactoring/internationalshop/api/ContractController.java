package com.saljuama.katas.refactoring.internationalshop.api;

import com.saljuama.katas.refactoring.internationalshop.model.Contract;
import com.saljuama.katas.refactoring.internationalshop.model.ContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
@AllArgsConstructor
public class ContractController {

  private final ContractRepository repository;

  @PostMapping
  public ResponseEntity<Contract> createNewContract(@RequestBody Contract contract) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(repository.save(contract));
  }

  @GetMapping("/{id}")
  public ResponseEntity<List<Contract>> getContractsByCustomer(@PathVariable("id") String customerId) {
    return ResponseEntity
        .ok(repository.findByCustomerId(customerId));
  }

}
