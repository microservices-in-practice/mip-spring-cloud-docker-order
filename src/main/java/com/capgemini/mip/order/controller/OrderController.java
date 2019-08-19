package com.capgemini.mip.order.controller;

import com.capgemini.mip.order.service.OrderCreationTO;
import com.capgemini.mip.order.service.OrderService;
import com.capgemini.mip.order.service.OrderTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

  @Autowired
  private OrderService service;

  @GetMapping
  public ResponseEntity<List<OrderTO>> getOrders() {
    return Optional.ofNullable(service.getAllOrders())
      .map(orders -> new ResponseEntity<>(orders, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping(path = "/{orderId}")
  public ResponseEntity<OrderTO> getOrder(@PathVariable String orderId) {
    return Optional.ofNullable(service.getOrderByOrderId(orderId))
      .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping()
  public ResponseEntity<OrderTO> createOrder(@RequestBody OrderCreationTO orderCreationTO) {
    return Optional.ofNullable(service.createOrder(orderCreationTO))
      .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
