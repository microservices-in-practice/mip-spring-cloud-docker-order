package com.capgemini.mip.order.service;

import com.capgemini.mip.order.client.catalog.CatalogClient;
import com.capgemini.mip.order.client.catalog.RemoteItemTO;
import com.capgemini.mip.order.client.customer.CustomerClient;
import com.capgemini.mip.order.client.customer.RemoteCustomerTO;
import com.capgemini.mip.order.domain.Order;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.capgemini.mip.order.testdata.TestdataProvider.provideRemoteCustomerTO;
import static com.capgemini.mip.order.testdata.TestdataProvider.provideRemoteItemTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@DirtiesContext
public class OrderServiceTest {

  @Autowired
  private OrderService orderService;

  @MockBean
  private CustomerClient customerClient;

  @MockBean
  private CatalogClient catalogClient;

  @Autowired
  private EntityManager entityManager;

  @Test
  public void shouldCreateOrder() {

    // given
    RemoteItemTO remoteItemTO = provideRemoteItemTO();
    when(catalogClient.getItemByCode(remoteItemTO.getCode())).thenReturn(remoteItemTO);
    RemoteCustomerTO remoteCustomerTO = provideRemoteCustomerTO();
    when(customerClient.getCustomerByCode(remoteCustomerTO.getCode())).thenReturn(remoteCustomerTO);

    OrderCreationTO orderCreationTO = OrderCreationTOBuilder.orderTO()
      .withCustomerCode(remoteCustomerTO.getCode())
      .withPositions(Sets.newHashSet(
        OrderCreationPositionTOBuilder.orderPositionTO()
          .withAmount(2)
          .withCode(remoteItemTO.getCode())
          .build()
      ))
      .build();

    // when
    OrderTO createdOrder = orderService.createOrder(orderCreationTO);

    // then
    Order order = entityManager.find(Order.class, createdOrder.getId());

    assertThat(order).isNotNull();
    assertThat(order.getCustomer().getName()).isEqualTo(remoteCustomerTO.getName());
    assertThat(order.getShippingAddress().getCity()).isEqualTo(remoteCustomerTO.getShippingAddress().getCity());
    assertThat(order.getPositions()).hasSize(1);
  }

  @Test
  public void shouldFindOrderByOrderId() {

    // given
    RemoteItemTO remoteItemTO = provideRemoteItemTO();
    when(catalogClient.getItemByCode(remoteItemTO.getCode())).thenReturn(remoteItemTO);
    RemoteCustomerTO remoteCustomerTO = provideRemoteCustomerTO();
    when(customerClient.getCustomerByCode(remoteCustomerTO.getCode())).thenReturn(remoteCustomerTO);

    OrderCreationTO orderCreationTO = OrderCreationTOBuilder.orderTO()
      .withCustomerCode(remoteCustomerTO.getCode())
      .withPositions(Sets.newHashSet(
        OrderCreationPositionTOBuilder.orderPositionTO()
          .withAmount(2)
          .withCode(remoteItemTO.getCode())
          .build()
      ))
      .build();

    OrderTO createdOrder = orderService.createOrder(orderCreationTO);

    // when
    OrderTO order = orderService.getOrderByOrderId(createdOrder.getOrderId());

    // then
    assertThat(order).isNotNull();
    assertThat(order.getCustomer().getName()).isEqualTo(remoteCustomerTO.getName());
    assertThat(order.getShippingAddress().getCity()).isEqualTo(remoteCustomerTO.getShippingAddress().getCity());
    assertThat(order.getPositions()).hasSize(1);
  }


}
