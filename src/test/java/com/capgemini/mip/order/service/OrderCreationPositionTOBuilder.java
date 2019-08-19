package com.capgemini.mip.order.service;

public final class OrderCreationPositionTOBuilder {
  private String code;
  private int amount;

  private OrderCreationPositionTOBuilder() {
  }

  public static OrderCreationPositionTOBuilder orderPositionTO() {
    return new OrderCreationPositionTOBuilder();
  }

  public OrderCreationPositionTOBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  public OrderCreationPositionTOBuilder withAmount(int amount) {
    this.amount = amount;
    return this;
  }

  public OrderCreationPositionTO build() {
    OrderCreationPositionTO orderCreationPositionTO = new OrderCreationPositionTO();
    orderCreationPositionTO.setCode(code);
    orderCreationPositionTO.setAmount(amount);
    return orderCreationPositionTO;
  }
}
