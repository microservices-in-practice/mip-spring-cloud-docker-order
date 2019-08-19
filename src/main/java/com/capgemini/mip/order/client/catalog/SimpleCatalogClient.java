package com.capgemini.mip.order.client.catalog;

import com.capgemini.mip.order.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Profile("dev")
public class SimpleCatalogClient implements CatalogClient {

  RestTemplate restTemplate = new RestTemplate();

  @Autowired
  private ConfigProperties properties;


  @Override
  public List<RemoteItemTO> getAllItems() {
    return Arrays.asList(restTemplate.getForObject(properties.getCatalogServiceUrl(), RemoteItemTO[].class));
  }

  @Override
  public RemoteItemTO getItemByCode(String code) {
    return restTemplate.getForObject(properties.getCatalogServiceUrl() + code, RemoteItemTO.class);
  }

}
