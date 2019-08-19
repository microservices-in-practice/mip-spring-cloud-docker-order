package com.capgemini.mip.order.domain.builders;

import com.capgemini.mip.order.domain.OrderPosition;

public final class OrderPositionBuilder {
  private Long id;
  private Integer version;
  private String code;
  private String name;
  private Double price;
  private int amount;

  private OrderPositionBuilder() {
  }

  public static OrderPositionBuilder orderPosition() {
    return new OrderPositionBuilder();
  }

  public OrderPositionBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public OrderPositionBuilder withVersion(Integer version) {
    this.version = version;
    return this;
  }

  public OrderPositionBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  public OrderPositionBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public OrderPositionBuilder withPrice(Double price) {
    this.price = price;
    return this;
  }

  public OrderPositionBuilder withAmount(int amount) {
    this.amount = amount;
    return this;
  }

  public OrderPosition build() {
    OrderPosition orderPosition = new OrderPosition();
    orderPosition.setId(id);
    orderPosition.setVersion(version);
    orderPosition.setCode(code);
    orderPosition.setName(name);
    orderPosition.setPrice(price);
    orderPosition.setAmount(amount);
    return orderPosition;
  }
}
