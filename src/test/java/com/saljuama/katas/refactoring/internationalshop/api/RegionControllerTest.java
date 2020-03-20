package com.saljuama.katas.refactoring.internationalshop.api;

import com.saljuama.katas.refactoring.internationalshop.ApiIntegrationTest;
import com.saljuama.katas.refactoring.internationalshop.model.Region;
import com.saljuama.katas.refactoring.internationalshop.model.RegionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class RegionControllerTest extends ApiIntegrationTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private RegionRepository repository;

  @Test
  public void canCreateANewRegion() throws Exception {
    when(repository.save(any(Region.class))).thenAnswer(invocation -> invocation.getArgument(0, Region.class).withId(1L));
    mockMvc
        .perform(post("/regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"name\": \"Valencia\", \"postalCodes\": \"46001,46002,46034\" }")
        )
        .andExpect(status().is(HttpStatus.CREATED.value()));
  }

  @Test
  void canListAllRegions() throws Exception {
    when(repository.findAll()).thenReturn(Collections.singletonList(new Region(1L, "Valencia", "46001,46002,46034")));
    mockMvc
        .perform(get("/regions"))
        .andExpect(status().is(HttpStatus.OK.value()))
        .andExpect(content().json("[ { \"name\": \"Valencia\", \"postalCodes\": \"46001,46002,46034\" } ]"));
  }
}