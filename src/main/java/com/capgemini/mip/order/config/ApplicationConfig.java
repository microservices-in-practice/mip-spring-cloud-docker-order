package com.capgemini.mip.order.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

  @Bean
  public DozerBeanMapper beanMapper() {
    return new DozerBeanMapper();
  }

  @Bean
  @Profile("cloud")
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

}
