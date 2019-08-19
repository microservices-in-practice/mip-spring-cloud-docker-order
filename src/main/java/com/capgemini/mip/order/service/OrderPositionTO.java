package com.capgemini.mip.order.service;

import java.io.Serializable;

public class OrderPositionTO implements Serializable {

  private Long id;
  private Integer version;
  private String code;
  private String name;
  private Double price;
  private int amount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "OrderPositionTO{" +
      "id=" + id +
      ", version=" + version +
      ", code='" + code + '\'' +
      ", name='" + name + '\'' +
      ", price=" + price +
      ", amount=" + amount +
      '}';
  }
}
