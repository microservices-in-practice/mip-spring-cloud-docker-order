package com.capgemini.mip.order.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@Configuration
@ConfigurationProperties(prefix = "order")
public class ConfigProperties {

  @Min(0)
  @Max(1)
  @NotNull
  private Double discount;

  @NotNull
  private String catalogServiceUrl;

  @NotNull
  private String customerServiceUrl;

  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public String getCatalogServiceUrl() {
    return catalogServiceUrl;
  }

  public void setCatalogServiceUrl(String catalogServiceUrl) {
    this.catalogServiceUrl = catalogServiceUrl;
  }

  public String getCustomerServiceUrl() {
    return customerServiceUrl;
  }

  public void setCustomerServiceUrl(String customerServiceUrl) {
    this.customerServiceUrl = customerServiceUrl;
  }
}
