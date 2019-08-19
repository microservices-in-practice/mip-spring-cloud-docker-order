package com.capgemini.mip.order.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Customer {
  private String code;
  private String name;
  private String vat;

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

  public String getVat() {
    return vat;
  }

  public void setVat(String vat) {
    this.vat = vat;
  }

  @Override
  public String toString() {
    return "Customer{" +
      "code='" + code + '\'' +
      ", name='" + name + '\'' +
      ", vat='" + vat + '\'' +
      '}';
  }
}
