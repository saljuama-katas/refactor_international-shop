package com.saljuama.katas.refactoring.internationalshop.api;

import com.saljuama.katas.refactoring.internationalshop.ApiIntegrationTest;
import com.saljuama.katas.refactoring.internationalshop.model.Purchase;
import com.saljuama.katas.refactoring.internationalshop.service.ArticlePurchaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ArticlesControllerTest extends ApiIntegrationTest {

  @MockBean private ArticlePurchaseService articlePurchaseService;
  @Autowired private MockMvc mockMvc;

  @Test
  void canCreateANewArticle() throws Exception {
    when(articlePurchaseService.distributeNewArticle(any())).thenReturn(Optional.of(new Purchase()));
    mockMvc
        .perform(post("/articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"name\": \"Rock CD\", \"category\": \"MUSIC\", \"description\": \"Awesome Rock Mix\", \"regionId\": 1 }")
        )
        .andExpect(status().is(HttpStatus.CREATED.value()));
  }

  @Test
  void returnsErrorWhenThereAreNoBuyersForNewArticle() throws Exception {
    when(articlePurchaseService.distributeNewArticle(any())).thenReturn(Optional.empty());
    mockMvc
        .perform(post("/articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"name\": \"Rock CD\", \"category\": \"MUSIC\", \"description\": \"Awesome Rock Mix\", \"regionId\": 1 }")
        )
        .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()));
  }

}
