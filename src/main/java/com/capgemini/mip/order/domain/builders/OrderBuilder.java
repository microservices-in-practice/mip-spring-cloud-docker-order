package com.capgemini.mip.order.domain.builders;

import com.capgemini.mip.order.domain.Address;
import com.capgemini.mip.order.domain.Customer;
import com.capgemini.mip.order.domain.Order;
import com.capgemini.mip.order.domain.OrderPosition;

import java.util.HashSet;
import java.util.Set;

public final class OrderBuilder {
  private Long id;
  private Integer version;
  private String orderId;
  private Customer customer;
  private Address shippingAddress;
  private Set<OrderPosition> positions = new HashSet<>();
  private boolean billed = false;

  private OrderBuilder() {
  }

  public static OrderBuilder order() {
    return new OrderBuilder();
  }

  public OrderBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public OrderBuilder withVersion(Integer version) {
    this.version = version;
    return this;
  }

  public OrderBuilder withOrderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

  public OrderBuilder withCustomer(Customer customer) {
    this.customer = customer;
    return this;
  }

  public OrderBuilder withShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
    return this;
  }

  public OrderBuilder withPositions(Set<OrderPosition> positions) {
    this.positions = positions;
    return this;
  }

  public OrderBuilder withBilled(boolean billed) {
    this.billed = billed;
    return this;
  }

  public Order build() {
    Order order = new Order();
    order.setId(id);
    order.setVersion(version);
    order.setOrderId(orderId);
    order.setCustomer(customer);
    order.setShippingAddress(shippingAddress);
    order.setPositions(positions);
    order.setBilled(billed);
    return order;
  }
}
