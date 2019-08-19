package com.capgemini.mip.order.service.customers;


import com.capgemini.mip.order.client.customer.CustomerClient;
import com.capgemini.mip.order.client.customer.RemoteCustomerTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.capgemini.mip.order.testdata.TestdataProvider.provideRemoteCustomerTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@DirtiesContext
public class ReadCustomerServiceTest {

  @Autowired
  private ReadCustomerService readCustomerService;

  @MockBean
  private CustomerClient customerClient;

  @Test
  public void shouldGetCustomerByCode() {

    // given
    RemoteCustomerTO remoteCustomerTO = provideRemoteCustomerTO();
    when(customerClient.getCustomerByCode(remoteCustomerTO.getCode())).thenReturn(remoteCustomerTO);

    // when

    CustomerTO customer = readCustomerService.getCustomerByCode(remoteCustomerTO.getCode());

    // then
    assertThat(customer).isNotNull();
    assertThat(customer.getCode()).isEqualTo(remoteCustomerTO.getCode());
  }

  @Test
  public void shouldGetAllCustomers() {

    // given
    RemoteCustomerTO remoteCustomerTO = provideRemoteCustomerTO();
    when(customerClient.getAllCustomers()).thenReturn(Arrays.asList(remoteCustomerTO));

    // when

    List<CustomerTO> customers = readCustomerService.getAllCustomers();

    // then
    assertThat(customers).isNotNull();
    assertThat(customers).hasSize(1);
    assertThat(customers.stream().map(CustomerTO::getCode).collect(Collectors.toList())).containsOnly(remoteCustomerTO.getCode());
  }


}
