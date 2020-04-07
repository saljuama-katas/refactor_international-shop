package com.saljuama.katas.refactoring.internationalshop.api;

import com.saljuama.katas.refactoring.internationalshop.ApiIntegrationTest;
import com.saljuama.katas.refactoring.internationalshop.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PurchasesControllerTest extends ApiIntegrationTest {

  @MockBean private PurchaseRepository repository;
  @Autowired private MockMvc mockMvc;

  @Test
  void canListPurchasesByCustomer() throws Exception {
    Region valencia = new Region(1L, "Valencia", "46001,46002");
    Article article1 = new Article(1L, "Name1", Category.MUSIC, "Description1", valencia);
    Contract contract1 = new Contract(1L, "customer1", valencia, Category.MUSIC, 10, LocalDate.of(2015, 10, 10), null);

    Region barcelona = new Region(2L, "Barcelona", "08001,08002");
    Article article2 = new Article(2L, "Name2", Category.MUSIC, "Description2", barcelona);
    Contract contract2 = new Contract(2L, "customer1", barcelona, Category.MUSIC, 10, LocalDate.of(2015, 10, 10), null);

    when(repository.findByContractCustomerId("customer1")).thenReturn(Arrays.asList(
        new Purchase(article1, contract1, Category.MUSIC, new BigDecimal("10.00")),
        new Purchase(article2, contract2, Category.MUSIC, new BigDecimal("10.00"))
    ));
    mockMvc
        .perform(get("/purchases/customer1"))
        .andExpect(status().is(HttpStatus.OK.value()))
        .andExpect(content().json("[" +
            "{\"createdDate\":null,\"lastModifiedDate\":null,\"id\":{\"articleId\":1,\"contractId\":1},\"article\":{\"createdDate\":null,\"lastModifiedDate\":null,\"id\":1,\"name\":\"Name1\",\"category\":\"MUSIC\",\"description\":\"Description1\",\"region\":{\"createdDate\":null,\"lastModifiedDate\":null,\"id\":1,\"name\":\"Valencia\",\"postalCodes\":\"46001,46002\"}},\"contract\":{\"createdDate\":null,\"lastModifiedDate\":null,\"id\":1,\"customerId\":\"customer1\",\"region\":{\"createdDate\":null,\"lastModifiedDate\":null,\"id\":1,\"name\":\"Valencia\",\"postalCodes\":\"46001,46002\"},\"category\":\"MUSIC\",\"weeklyLimit\":10,\"startDate\":\"2015-10-10\",\"endDate\":null},\"category\":\"MUSIC\",\"price\":10.00}," +
            "{\"createdDate\":null,\"lastModifiedDate\":null,\"id\":{\"articleId\":2,\"contractId\":2},\"article\":{\"createdDate\":null,\"lastModifiedDate\":null,\"id\":2,\"name\":\"Name2\",\"category\":\"MUSIC\",\"description\":\"Description2\",\"region\":{\"createdDate\":null,\"lastModifiedDate\":null,\"id\":2,\"name\":\"Barcelona\",\"postalCodes\":\"08001,08002\"}},\"contract\":{\"createdDate\":null,\"lastModifiedDate\":null,\"id\":2,\"customerId\":\"customer1\",\"region\":{\"createdDate\":null,\"lastModifiedDate\":null,\"id\":2,\"name\":\"Barcelona\",\"postalCodes\":\"08001,08002\"},\"category\":\"MUSIC\",\"weeklyLimit\":10,\"startDate\":\"2015-10-10\",\"endDate\":null},\"category\":\"MUSIC\",\"price\":10.00}" +
            "]"));
  }

}
