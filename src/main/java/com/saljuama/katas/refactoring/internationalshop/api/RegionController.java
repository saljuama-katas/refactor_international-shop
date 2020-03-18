package com.saljuama.katas.refactoring.internationalshop.api;

import com.saljuama.katas.refactoring.internationalshop.model.Region;
import com.saljuama.katas.refactoring.internationalshop.model.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
@AllArgsConstructor
public class RegionController {

  private final RegionRepository repository;

  @GetMapping
  public ResponseEntity<List<Region>> getRegions() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(repository.findAll());
  }

  @PostMapping
  public ResponseEntity<Region> createNewRegion(@RequestBody Region region) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(repository.save(region));
  }

//  @GetMapping("/{id}")
//  public Optional<Region> getRegionById(@PathVariable("id") Long id) {
//    return Optional.empty();
//  }

}
