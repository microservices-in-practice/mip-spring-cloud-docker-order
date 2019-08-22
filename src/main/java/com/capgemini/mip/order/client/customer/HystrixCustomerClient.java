package com.capgemini.mip.order.client.customer;

import com.capgemini.mip.order.config.ConfigProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Profile("hystrix")
public class HystrixCustomerClient implements CustomerClient {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  private ConfigProperties properties;

  @Override
  @HystrixCommand(
    fallbackMethod = "getAllCustomersFallback",
    commandProperties = {
      @HystrixProperty(
        name = "execution.isolation.thread.timeoutInMilliseconds",
        value = "500")
    })
  public List<RemoteCustomerTO> getAllCustomers() {
    return restTemplate.exchange(properties.getCustomerServiceUrl(), HttpMethod.GET, null,
      new ParameterizedTypeReference<List<RemoteCustomerTO>>() {
      })
      .getBody();
  }

  public List<RemoteCustomerTO> getAllCustomersFallback() {
    return Collections.emptyList();
  }

  @Override
  @HystrixCommand(
    fallbackMethod = "getCustomerByCodeFallback",
    commandProperties = {
      @HystrixProperty(
        name = "execution.isolation.thread.timeoutInMilliseconds",
        value = "500")
    })
  public RemoteCustomerTO getCustomerByCode(String code) {
    return restTemplate.getForObject(properties.getCustomerServiceUrl() + code, RemoteCustomerTO.class);
  }

  public RemoteCustomerTO getCustomerByCodeFallback(String code) {
    return null;
  }

}
