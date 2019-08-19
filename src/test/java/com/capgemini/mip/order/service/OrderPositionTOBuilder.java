package com.capgemini.mip.order.service;

public final class OrderPositionTOBuilder {
  private Long id;
  private Integer version;
  private String code;
  private String name;
  private Double price;
  private int amount;

  private OrderPositionTOBuilder() {
  }

  public static OrderPositionTOBuilder orderPositionTO() {
    return new OrderPositionTOBuilder();
  }

  public OrderPositionTOBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public OrderPositionTOBuilder withVersion(Integer version) {
    this.version = version;
    return this;
  }

  public OrderPositionTOBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  public OrderPositionTOBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public OrderPositionTOBuilder withPrice(Double price) {
    this.price = price;
    return this;
  }

  public OrderPositionTOBuilder withAmount(int amount) {
    this.amount = amount;
    return this;
  }

  public OrderPositionTO build() {
    OrderPositionTO orderPositionTO = new OrderPositionTO();
    orderPositionTO.setId(id);
    orderPositionTO.setVersion(version);
    orderPositionTO.setCode(code);
    orderPositionTO.setName(name);
    orderPositionTO.setPrice(price);
    orderPositionTO.setAmount(amount);
    return orderPositionTO;
  }
}
