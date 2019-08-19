package com.capgemini.mip.order.service.items;

import com.capgemini.mip.order.client.catalog.CatalogClient;
import com.capgemini.mip.order.client.catalog.RemoteItemTO;
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

import static com.capgemini.mip.order.testdata.TestdataProvider.provideRemoteItemTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = {"order.discount=0.5"})
@Transactional
@DirtiesContext
public class ReadItemServiceTest {

  @Autowired
  private ReadItemService readItemService;

  @MockBean
  private CatalogClient catalogClient;

  @Test
  public void shouldGetItemByCode() {

    // given
    RemoteItemTO remoteItemTO = provideRemoteItemTO();
    when(catalogClient.getItemByCode(remoteItemTO.getCode())).thenReturn(remoteItemTO);

    // when

    ItemTO item = readItemService.getItemByCode(remoteItemTO.getCode());

    // then
    assertThat(item).isNotNull();
    assertThat(item.getCode()).isEqualTo(remoteItemTO.getCode());
  }

  @Test
  public void shouldGetAllItems() {

    // given
    RemoteItemTO remoteItemTO = provideRemoteItemTO();
    when(catalogClient.getAllItems()).thenReturn(Arrays.asList(remoteItemTO));

    // when

    List<ItemTO> items = readItemService.getAllItems();

    // then
    assertThat(items).isNotNull();
    assertThat(items).hasSize(1);
    assertThat(items.stream().map(ItemTO::getCode).collect(Collectors.toList())).containsOnly(remoteItemTO.getCode());
  }

  @Test
  public void shouldApplyDiscount() {

    // given
    RemoteItemTO remoteItemTO = provideRemoteItemTO();
    when(catalogClient.getItemByCode(remoteItemTO.getCode())).thenReturn(remoteItemTO);

    // when

    ItemTO item = readItemService.getItemByCode(remoteItemTO.getCode());

    // then
    assertThat(item).isNotNull();
    assertThat(item.getCode()).isEqualTo(remoteItemTO.getCode());
    assertThat(item.getPrice()).isEqualTo(remoteItemTO.getPrice() * 0.5);
  }

}
