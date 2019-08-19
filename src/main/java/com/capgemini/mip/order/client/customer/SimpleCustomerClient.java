package com.capgemini.mip.order.client.customer;

import com.capgemini.mip.order.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Profile("dev")
public class SimpleCustomerClient implements CustomerClient {

  RestTemplate restTemplate = new RestTemplate();

  @Autowired
  private ConfigProperties properties;

  @Override
  public List<RemoteCustomerTO> getAllCustomers() {
    return restTemplate.exchange(properties.getCustomerServiceUrl(), HttpMethod.GET, null,
      new ParameterizedTypeReference<List<RemoteCustomerTO>>() {
      })
      .getBody();
  }

  @Override
  public RemoteCustomerTO getCustomerByCode(String code) {
    return restTemplate.getForObject(properties.getCustomerServiceUrl() + code, RemoteCustomerTO.class);
  }

}
