package com.capgemini.mip.order.client.catalog;

public final class RemoteItemTOBuilder {
  private Long id;
  private Integer version;
  private String code;
  private String name;
  private Double price;
  private String description;

  private RemoteItemTOBuilder() {
  }

  public static RemoteItemTOBuilder item() {
    return new RemoteItemTOBuilder();
  }

  public RemoteItemTOBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public RemoteItemTOBuilder withVersion(Integer version) {
    this.version = version;
    return this;
  }

  public RemoteItemTOBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  public RemoteItemTOBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public RemoteItemTOBuilder withPrice(Double price) {
    this.price = price;
    return this;
  }

  public RemoteItemTOBuilder withDescription(String description) {
    this.description = description;
    return this;
  }

  public RemoteItemTO build() {
    RemoteItemTO remoteItemTO = new RemoteItemTO();
    remoteItemTO.setId(id);
    remoteItemTO.setVersion(version);
    remoteItemTO.setCode(code);
    remoteItemTO.setName(name);
    remoteItemTO.setPrice(price);
    remoteItemTO.setDescription(description);
    return remoteItemTO;
  }
}
