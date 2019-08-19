package com.capgemini.mip.order.controller;

import com.capgemini.mip.order.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.capgemini.mip.order.testdata.TestdataProvider.provideOrderPositionTO;
import static com.capgemini.mip.order.testdata.TestdataProvider.provideOrderTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class)
public class OrderControllerUnitTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private OrderService orderService;

  @Test
  public void shouldCreateOrder() throws Exception {
    OrderTO mockOrderTO = provideOrderTO();
    OrderPositionTO mockOrderPositionTO = provideOrderPositionTO();
    mockOrderTO.getPositions().add(mockOrderPositionTO);

    when(orderService.createOrder(any(OrderCreationTO.class))).thenReturn(mockOrderTO);

    OrderCreationTO creationTO = OrderCreationTOBuilder.orderTO()
      .withCustomerCode(mockOrderTO.getCustomer().getCode())
      .withPositions(Sets.newHashSet(
        OrderCreationPositionTOBuilder.orderPositionTO()
          .withCode(mockOrderPositionTO.getCode())
          .withAmount(2)
          .build()
      ))
      .build();


    this.mvc.perform(post("/orders/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(toJson(creationTO)))
      .andExpect(status().isOk())
      .andExpect(content().json(toJson(mockOrderTO)));

  }

  private static String toJson(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }


}
