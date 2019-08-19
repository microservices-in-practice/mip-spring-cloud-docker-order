package com.capgemini.mip.order.service.items;

import com.capgemini.mip.order.client.catalog.CatalogClient;
import com.capgemini.mip.order.config.ConfigProperties;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReadItemService {
  @Autowired
  private ConfigProperties properties;

  @Autowired
  private CatalogClient catalogClient;

  @Autowired
  private DozerBeanMapper beanMapper;

  public List<ItemTO> getAllItems() {
    return catalogClient.getAllItems().stream()
      .map(item -> beanMapper.map(item, ItemTO.class))
      .map(this::applyDiscountRate)
      .collect(Collectors.toList());
  }

  public ItemTO getItemByCode(String code) {
    return Optional.ofNullable(catalogClient.getItemByCode(code))
      .map(item -> beanMapper.map(item, ItemTO.class))
      .map(this::applyDiscountRate)
      .orElse(null);
  }


  private ItemTO applyDiscountRate(ItemTO item) {
    item.setPrice(item.getPrice() * (1.0 - properties.getDiscount()));
    return item;
  }
}
