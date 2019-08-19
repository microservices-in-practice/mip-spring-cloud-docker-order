package com.capgemini.mip.order.service;

import java.io.Serializable;

public class OrderCreationPositionTO implements Serializable {
  
  private String code;
  private int amount;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
