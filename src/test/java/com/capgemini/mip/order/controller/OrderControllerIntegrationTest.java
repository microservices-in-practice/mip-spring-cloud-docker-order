package com.capgemini.mip.order.controller;

import com.capgemini.mip.order.client.catalog.RemoteItemTO;
import com.capgemini.mip.order.client.customer.RemoteCustomerTO;
import com.capgemini.mip.order.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.collect.Sets;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;

import static com.capgemini.mip.order.testdata.TestdataProvider.provideRemoteCustomerTO;
import static com.capgemini.mip.order.testdata.TestdataProvider.provideRemoteItemTO;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
  "order.catalogServiceUrl: http://localhost:9091/items/",
  "order.customerServiceUrl: http://localhost:9091/customers/"})
@AutoConfigureMockMvc
@DirtiesContext
public class OrderControllerIntegrationTest {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(9091));

  @Autowired
  private MockMvc mvc;

  @Autowired
  private OrderService orderService;

  @Before
  public void init() throws JsonProcessingException {
    RemoteItemTO remoteItemTO = provideRemoteItemTO();
    stubFor(get(urlEqualTo("/items/" + remoteItemTO.getCode()))
      .willReturn(aResponse()
        .withStatus(HttpStatus.OK.value())
        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .withBody(toJson(remoteItemTO))));

    RemoteCustomerTO remoteCustomerTO = provideRemoteCustomerTO();
    stubFor(get(urlEqualTo("/customers/" + remoteCustomerTO.getCode()))
      .willReturn(aResponse()
        .withStatus(HttpStatus.OK.value())
        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .withBody(toJson(remoteCustomerTO))));
  }

  @Test
  public void shouldCreateOrder() throws Exception {
    OrderCreationTO creationTO = OrderCreationTOBuilder.orderTO()
      .withCustomerCode(provideRemoteCustomerTO().getCode())
      .withPositions(Sets.newHashSet(
        OrderCreationPositionTOBuilder.orderPositionTO()
          .withCode(provideRemoteItemTO().getCode())
          .withAmount(2)
          .build()
      ))
      .build();


    ResultActions resultActions = this.mvc.perform(post("/orders/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(toJson(creationTO)))
      .andExpect(status().isOk());

    OrderTO orderTO = toOrder(resultActions.andReturn().getResponse().getContentAsString());
    Assertions.assertThat(orderService.getOrderByOrderId(orderTO.getOrderId())).isNotNull();
  }

  private OrderTO toOrder(String json) throws IOException {
    return new ObjectMapper().readValue(json, OrderTO.class);
  }

  private static String toJson(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }


}
