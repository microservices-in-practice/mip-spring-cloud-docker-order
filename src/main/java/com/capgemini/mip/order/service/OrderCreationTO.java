package com.capgemini.mip.order.service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class OrderCreationTO implements Serializable {

  private String customerCode;
  private Set<OrderCreationPositionTO> positions = new HashSet<>();

  public String getCustomerCode() {
    return customerCode;
  }

  public void setCustomerCode(String customerCode) {
    this.customerCode = customerCode;
  }

  public Set<OrderCreationPositionTO> getPositions() {
    return positions;
  }

  public void setPositions(Set<OrderCreationPositionTO> positions) {
    this.positions = positions;
  }
}
