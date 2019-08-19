package com.capgemini.mip.order.controller;

import com.capgemini.mip.order.service.customers.CustomerTO;
import com.capgemini.mip.order.service.customers.ReadCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class ReadCustomerController {

  @Autowired
  private ReadCustomerService service;


  @GetMapping(path = "/{code}")
  public ResponseEntity<CustomerTO> getCustomer(@PathVariable String code) {
    return Optional.ofNullable(service.getCustomerByCode(code))
      .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping()
  public ResponseEntity<List<CustomerTO>> getCustomers() {
    return Optional.ofNullable(service.getAllCustomers())
      .map(customers -> new ResponseEntity<>(customers, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
