package com.capgemini.mip.order.client.customer;

public final class RemoteAddressTOBuilder {
  private String street;
  private String city;
  private String zip;
  private String state;

  private RemoteAddressTOBuilder() {
  }

  public static RemoteAddressTOBuilder address() {
    return new RemoteAddressTOBuilder();
  }

  public RemoteAddressTOBuilder withStreet(String street) {
    this.street = street;
    return this;
  }

  public RemoteAddressTOBuilder withCity(String city) {
    this.city = city;
    return this;
  }

  public RemoteAddressTOBuilder withZip(String zip) {
    this.zip = zip;
    return this;
  }

  public RemoteAddressTOBuilder withState(String state) {
    this.state = state;
    return this;
  }

  public RemoteAddressTO build() {
    RemoteAddressTO remoteAddressTO = new RemoteAddressTO();
    remoteAddressTO.setStreet(street);
    remoteAddressTO.setCity(city);
    remoteAddressTO.setZip(zip);
    remoteAddressTO.setState(state);
    return remoteAddressTO;
  }
}
