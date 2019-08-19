package com.capgemini.mip.order.domain.builders;

import com.capgemini.mip.order.domain.Customer;

public final class CustomerBuilder {
  private String code;
  private String name;
  private String vat;

  private CustomerBuilder() {
  }

  public static CustomerBuilder customer() {
    return new CustomerBuilder();
  }

  public CustomerBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  public CustomerBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public CustomerBuilder withVat(String vat) {
    this.vat = vat;
    return this;
  }

  public Customer build() {
    Customer customer = new Customer();
    customer.setCode(code);
    customer.setName(name);
    customer.setVat(vat);
    return customer;
  }
}
