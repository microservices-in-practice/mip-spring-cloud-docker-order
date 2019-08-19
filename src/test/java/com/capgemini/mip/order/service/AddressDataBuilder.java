package com.capgemini.mip.order.service;

import static com.capgemini.mip.order.service.OrderTO.AddressData;

public final class AddressDataBuilder {
  private String street;
  private String city;
  private String zip;
  private String state;

  private AddressDataBuilder() {
  }

  public static AddressDataBuilder addressData() {
    return new AddressDataBuilder();
  }

  public AddressDataBuilder withStreet(String street) {
    this.street = street;
    return this;
  }

  public AddressDataBuilder withCity(String city) {
    this.city = city;
    return this;
  }

  public AddressDataBuilder withZip(String zip) {
    this.zip = zip;
    return this;
  }

  public AddressDataBuilder withState(String state) {
    this.state = state;
    return this;
  }

  public AddressData build() {
    AddressData address = new AddressData();
    address.setStreet(street);
    address.setCity(city);
    address.setZip(zip);
    address.setState(state);
    return address;
  }
}
