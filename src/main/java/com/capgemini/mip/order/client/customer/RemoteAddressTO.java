package com.capgemini.mip.order.client.customer;

import java.io.Serializable;

public class RemoteAddressTO implements Serializable {
  private String street;
  private String city;
  private String zip;
  private String state;

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "RemoteAddressTO{" +
      "street='" + street + '\'' +
      ", city='" + city + '\'' +
      ", zip='" + zip + '\'' +
      ", state='" + state + '\'' +
      '}';
  }
}
