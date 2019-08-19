package com.capgemini.mip.order.repository;

import com.capgemini.mip.order.domain.Order;
import com.capgemini.mip.order.domain.OrderPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.capgemini.mip.order.testdata.TestdataProvider.provideOrder;
import static com.capgemini.mip.order.testdata.TestdataProvider.provideOrderPosition;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private EntityManager entityManager;

  @Test
  public void shouldCreateOrder() {

    // given
    Order orderEntity = provideOrder();
    OrderPosition orderPositionEntity = provideOrderPosition();
    orderEntity.getPositions().add(orderPositionEntity);

    // when
    Order savedOrder = orderRepository.save(orderEntity);

    // then
    Order foundOrder = entityManager.find(Order.class, savedOrder.getId());

    assertThat(foundOrder).isNotNull();
    assertThat(foundOrder.getCustomer().getCode()).isEqualTo(orderEntity.getCustomer().getCode());
    assertThat(foundOrder.getPositions()).hasSize(1);
    assertThat(foundOrder.getPositions().stream()
      .map(OrderPosition::getCode).collect(Collectors.toList())).containsOnly(orderPositionEntity.getCode());
  }

  @Test
  public void shouldFindByCustomerCode() {

    // given
    Order orderEntity = provideOrder();
    OrderPosition orderPositionEntity = provideOrderPosition();
    orderEntity.getPositions().add(orderPositionEntity);
    Order savedOrder = orderRepository.save(orderEntity);

    // when
    List<Order> orders = orderRepository.findByCustomerCode(orderEntity.getCustomer().getCode());

    // then

    assertThat(orders).isNotNull();
    assertThat(orders).hasSize(1);
    assertThat(orders.get(0).getCustomer().getName()).isEqualTo(orderEntity.getCustomer().getName());
  }

  @Test
  public void shouldFindByOrderId() {

    // given
    Order orderEntity = provideOrder();
    OrderPosition orderPositionEntity = provideOrderPosition();
    orderEntity.getPositions().add(orderPositionEntity);
    Order savedOrder = orderRepository.save(orderEntity);

    // when
    Order order = orderRepository.findByOrderId(orderEntity.getOrderId());

    // then

    assertThat(order).isNotNull();
    assertThat(order.getId()).isEqualTo(savedOrder.getId());
  }

}
