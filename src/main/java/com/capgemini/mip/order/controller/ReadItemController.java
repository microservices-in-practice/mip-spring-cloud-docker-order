package com.capgemini.mip.order.controller;

import com.capgemini.mip.order.service.items.ItemTO;
import com.capgemini.mip.order.service.items.ReadItemService;
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
@RequestMapping("/items")
public class ReadItemController {

  @Autowired
  private ReadItemService service;

  @GetMapping(path = "/{code}")
  public ResponseEntity<ItemTO> getItem(@PathVariable String code) {
    return Optional.ofNullable(service.getItemByCode(code))
      .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping()
  public ResponseEntity<List<ItemTO>> getItems() {
    return Optional.ofNullable(service.getAllItems())
      .map(items -> new ResponseEntity<>(items, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
