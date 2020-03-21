package com.saljuama.katas.refactoring.internationalshop.api;

import com.saljuama.katas.refactoring.internationalshop.ApiIntegrationTest;
import com.saljuama.katas.refactoring.internationalshop.model.Category;
import com.saljuama.katas.refactoring.internationalshop.model.Contract;
import com.saljuama.katas.refactoring.internationalshop.model.ContractRepository;
import com.saljuama.katas.refactoring.internationalshop.model.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ContractControllerTest extends ApiIntegrationTest {

  @MockBean private ContractRepository repository;
  @Autowired private MockMvc mockMvc;

  @Test
  void canCreateANewContract() throws Exception {
    mockMvc
        .perform(post("/contracts")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"customerId\": \"customer1\", \"regionId\": 1, \"category\": \"MUSIC\", \"weeklyLimit\": 10, \"startDate\": \"2020-03-20\" }")
        )
        .andExpect(status().is(HttpStatus.CREATED.value()));
  }

  @Test
  void canFindContractsByCustomer() throws Exception {
    Region region = new Region(1L, "region", "postcodes");
    Contract contract = new Contract("customer1", region, Category.MUSIC, 10, LocalDate.of(2020, 3, 20), null);
    when(repository.findByCustomerId("customer1")).thenReturn(Collections.singletonList(contract));

    mockMvc
        .perform(get("/contracts/customer1"))
        .andExpect(status().is(HttpStatus.OK.value()))
        .andExpect(content().json("[ { \"customerId\": \"customer1\", \"region\": {\"id\": 1}, \"category\": \"MUSIC\", \"weeklyLimit\": 10, \"startDate\": \"2020-03-20\" } ]"));
  }

}
