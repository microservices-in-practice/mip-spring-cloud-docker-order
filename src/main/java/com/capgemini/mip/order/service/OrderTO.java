package com.capgemini.mip.order.service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class OrderTO implements Serializable {

  private Long id;
  private Integer version;
  private String orderId;
  private CustomerData customer;
  private AddressData shippingAddress;
  private Set<OrderPositionTO> positions = new HashSet<>();

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

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public CustomerData getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerData customer) {
    this.customer = customer;
  }

  public AddressData getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(AddressData shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public Set<OrderPositionTO> getPositions() {
    return positions;
  }

  public void setPositions(Set<OrderPositionTO> positions) {
    this.positions = positions;
  }

  public static class CustomerData {
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
  }

  public static class AddressData {
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
  }
}
