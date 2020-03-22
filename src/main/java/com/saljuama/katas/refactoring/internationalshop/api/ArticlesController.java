package com.saljuama.katas.refactoring.internationalshop.api;


import com.saljuama.katas.refactoring.internationalshop.model.Article;
import com.saljuama.katas.refactoring.internationalshop.service.ArticlePurchaseService;
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

  private final ArticlePurchaseService service;

  @PostMapping
  public ResponseEntity<Article> createNewArticle(@RequestBody Article article) {
    return service
        .distributeNewArticle(article)
        .map(purchase -> ResponseEntity
            .status(HttpStatus.CREATED)
            .body(purchase.getArticle())
        )
        .orElseGet(() -> ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(article)
        );
  }

}
