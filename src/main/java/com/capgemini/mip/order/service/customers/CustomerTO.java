package com.capgemini.mip.order.service.customers;

import java.io.Serializable;

public class CustomerTO implements Serializable {

  private String code;
  private String name;
  private String vat;
  private AddressTO shippingAddress;

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

  public AddressTO getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(AddressTO shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

}
