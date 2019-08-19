package com.capgemini.mip.order.service;

import static com.capgemini.mip.order.service.OrderTO.CustomerData;

public final class CustomerDataBuilder {
  private String code;
  private String name;
  private String vat;

  private CustomerDataBuilder() {
  }

  public static CustomerDataBuilder customerData() {
    return new CustomerDataBuilder();
  }

  public CustomerDataBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  public CustomerDataBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public CustomerDataBuilder withVat(String vat) {
    this.vat = vat;
    return this;
  }

  public CustomerData build() {
    CustomerData customer = new CustomerData();
    customer.setCode(code);
    customer.setName(name);
    customer.setVat(vat);
    return customer;
  }
}
