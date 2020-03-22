package com.saljuama.katas.refactoring.internationalshop.api;

import com.saljuama.katas.refactoring.internationalshop.ApiIntegrationTest;
import com.saljuama.katas.refactoring.internationalshop.model.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ArticlesControllerTest extends ApiIntegrationTest {

  @MockBean private ArticleRepository repository;
  @Autowired private MockMvc mockMvc;

  @Test
  void canCreateANewArticle() throws Exception {
    mockMvc
        .perform(post("/articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"name\": \"Rock CD\", \"category\": \"MUSIC\", \"description\": \"Awesome Rock Mix\", \"regionId\": 1 }")
        )
        .andExpect(status().is(HttpStatus.CREATED.value()));
  }

}
