package com.capgemini.mip.order.service;

public class OrderCreationException extends RuntimeException {
  public OrderCreationException(String message) {
    super(message);
  }
}
