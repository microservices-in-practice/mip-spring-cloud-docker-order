package com.capgemini.mip.order.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ordering")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Version
  @Column(name = "version")
  private Integer version;

  @Column(nullable = false)
  private String orderId;

  @Embedded
  @AttributeOverrides(value = {
    @AttributeOverride(name = "code", column = @Column(name = "customer_code", nullable = false)),
    @AttributeOverride(name = "name", column = @Column(name = "customer_name", nullable = false)),
    @AttributeOverride(name = "vat", column = @Column(name = "customer_vat", nullable = false))
  })
  private Customer customer;

  @Embedded
  @AttributeOverrides(value = {
    @AttributeOverride(name = "street", column = @Column(name = "shipping_street", nullable = false)),
    @AttributeOverride(name = "city", column = @Column(name = "shipping_city", nullable = false)),
    @AttributeOverride(name = "zip", column = @Column(name = "shipping_zip", nullable = false, length = 6)),
    @AttributeOverride(name = "state", column = @Column(name = "shipping_state", nullable = false))
  })
  private Address shippingAddress;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "orderingId")
  private Set<OrderPosition> positions = new HashSet<>();

  @Column(nullable = false)
  private boolean billed = false;

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

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public Set<OrderPosition> getPositions() {
    return positions;
  }

  public void setPositions(Set<OrderPosition> positions) {
    this.positions = positions;
  }

  public boolean isBilled() {
    return billed;
  }

  public void setBilled(boolean billed) {
    this.billed = billed;
  }

  @Override
  public String toString() {
    return "Order{" +
      "id=" + id +
      ", version=" + version +
      ", orderId='" + orderId + '\'' +
      ", customer=" + customer +
      ", shippingAddress=" + shippingAddress +
      ", positions=" + positions +
      ", billed=" + billed +
      '}';
  }
}
