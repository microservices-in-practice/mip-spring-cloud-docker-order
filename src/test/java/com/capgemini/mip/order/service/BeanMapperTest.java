package com.capgemini.mip.order.service;

import com.capgemini.mip.order.config.ApplicationConfig;
import com.capgemini.mip.order.domain.Order;
import com.capgemini.mip.order.domain.OrderPosition;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

import static com.capgemini.mip.order.testdata.TestdataProvider.provideOrder;
import static com.capgemini.mip.order.testdata.TestdataProvider.provideOrderPosition;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class BeanMapperTest {

  @Autowired
  private DozerBeanMapper beanMapper;

  @Test
  public void shouldMapOrderIntoOrderTo() {
    Order order = provideOrder();
    OrderPosition orderPosition = provideOrderPosition();
    order.getPositions().add(orderPosition);
    OrderTO orderTO = beanMapper.map(order, OrderTO.class);

    assertThat(orderTO).isNotNull();
    assertThat(orderTO.getOrderId()).isEqualTo(order.getOrderId());
    assertThat(orderTO.getShippingAddress()).isNotNull();
    assertThat(orderTO.getShippingAddress().getCity()).isEqualTo(order.getShippingAddress().getCity());
    assertThat(orderTO.getCustomer()).isNotNull();
    assertThat(orderTO.getCustomer().getCode()).isEqualTo(order.getCustomer().getCode());
    assertThat(order.getPositions()).hasSize(order.getPositions().size());
    assertThat(orderTO.getPositions().stream().map(OrderPositionTO::getCode).collect(Collectors.toList()))
      .containsOnly(orderPosition.getCode());
  }

}
