package com.saljuama.katas.refactoring.internationalshop.api;


import com.saljuama.katas.refactoring.internationalshop.model.Article;
import com.saljuama.katas.refactoring.internationalshop.model.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticlesController {

  private final ArticleRepository repository;

  @PostMapping
  public ResponseEntity<Article> createNewArticle(@RequestBody Article article) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(repository.save(article));
  }

}
