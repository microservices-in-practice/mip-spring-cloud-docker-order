package com.capgemini.mip.order.service.customers;

import com.capgemini.mip.order.client.customer.CustomerClient;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReadCustomerService {

  @Autowired
  private CustomerClient customerClient;

  @Autowired
  private DozerBeanMapper beanMapper;

  public List<CustomerTO> getAllCustomers() {
    return customerClient.getAllCustomers().stream()
      .map(customer -> beanMapper.map(customer, CustomerTO.class))
      .collect(Collectors.toList());
  }

  public CustomerTO getCustomerByCode(String code) {
    return Optional.ofNullable(customerClient.getCustomerByCode(code))
      .map(customer -> beanMapper.map(customer, CustomerTO.class))
      .orElse(null);
  }
}
