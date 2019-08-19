package com.capgemini.mip.order.service;

import java.util.HashSet;
import java.util.Set;

public final class OrderTOBuilder {
  private Long id;
  private Integer version;
  private String orderId;
  private OrderTO.CustomerData customer;
  private OrderTO.AddressData shippingAddress;
  private Set<OrderPositionTO> positions = new HashSet<>();

  private OrderTOBuilder() {
  }

  public static OrderTOBuilder orderTO() {
    return new OrderTOBuilder();
  }

  public OrderTOBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public OrderTOBuilder withVersion(Integer version) {
    this.version = version;
    return this;
  }

  public OrderTOBuilder withOrderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

  public OrderTOBuilder withCustomer(OrderTO.CustomerData customer) {
    this.customer = customer;
    return this;
  }

  public OrderTOBuilder withShippingAddress(OrderTO.AddressData shippingAddress) {
    this.shippingAddress = shippingAddress;
    return this;
  }

  public OrderTOBuilder withPositions(Set<OrderPositionTO> positions) {
    this.positions = positions;
    return this;
  }

  public OrderTO build() {
    OrderTO orderTO = new OrderTO();
    orderTO.setId(id);
    orderTO.setVersion(version);
    orderTO.setOrderId(orderId);
    orderTO.setCustomer(customer);
    orderTO.setShippingAddress(shippingAddress);
    orderTO.setPositions(positions);
    return orderTO;
  }
}
