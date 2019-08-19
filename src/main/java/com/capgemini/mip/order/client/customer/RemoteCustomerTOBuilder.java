package com.capgemini.mip.order.client.customer;

public final class RemoteCustomerTOBuilder {
  private Long id;
  private Integer version;
  private String code;
  private String name;
  private String vat;
  private RemoteAddressTO shippingAddress;
  private RemoteAddressTO billingAddress;

  private RemoteCustomerTOBuilder() {
  }

  public static RemoteCustomerTOBuilder customer() {
    return new RemoteCustomerTOBuilder();
  }

  public RemoteCustomerTOBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public RemoteCustomerTOBuilder withVersion(Integer version) {
    this.version = version;
    return this;
  }

  public RemoteCustomerTOBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  public RemoteCustomerTOBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public RemoteCustomerTOBuilder withVat(String vat) {
    this.vat = vat;
    return this;
  }

  public RemoteCustomerTOBuilder withShippingAddress(RemoteAddressTO shippingAddress) {
    this.shippingAddress = shippingAddress;
    return this;
  }

  public RemoteCustomerTOBuilder withBillingAddress(RemoteAddressTO billingAddress) {
    this.billingAddress = billingAddress;
    return this;
  }

  public RemoteCustomerTO build() {
    RemoteCustomerTO remoteCustomerTO = new RemoteCustomerTO();
    remoteCustomerTO.setId(id);
    remoteCustomerTO.setVersion(version);
    remoteCustomerTO.setCode(code);
    remoteCustomerTO.setName(name);
    remoteCustomerTO.setVat(vat);
    remoteCustomerTO.setShippingAddress(shippingAddress);
    remoteCustomerTO.setBillingAddress(billingAddress);
    return remoteCustomerTO;
  }
}
