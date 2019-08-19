package com.capgemini.mip.order.service.items;

import com.capgemini.mip.order.client.catalog.RemoteItemTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.capgemini.mip.order.testdata.TestdataProvider.provideRemoteItemTO;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = {"order.catalogServiceUrl: http://localhost:9091/items/"})
@Transactional
@DirtiesContext
public class ReadItemServiceWiremockTest {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(9091));

  @Autowired
  private ReadItemService readItemService;

  @Test
  public void shouldGetItemByCode() throws JsonProcessingException {

    // given
    RemoteItemTO remoteItemTO = provideRemoteItemTO();

    stubFor(get(urlEqualTo("/items/" + remoteItemTO.getCode()))
      .willReturn(aResponse()
        .withStatus(HttpStatus.OK.value())
        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .withBody(toJson(remoteItemTO))));

    // when
    ItemTO item = readItemService.getItemByCode(remoteItemTO.getCode());

    // then
    assertThat(item).isNotNull();
    assertThat(item.getCode()).isEqualTo(remoteItemTO.getCode());

    verify(getRequestedFor(urlMatching("/items/" + remoteItemTO.getCode())));
  }

  @Test
  public void shouldGetAllItems() throws JsonProcessingException {

    // given
    RemoteItemTO remoteItemTO = provideRemoteItemTO();
    stubFor(get(urlEqualTo("/items/"))
      .willReturn(aResponse()
        .withStatus(HttpStatus.OK.value())
        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .withBody(toJson(Arrays.asList(remoteItemTO)))));

    // when

    List<ItemTO> items = readItemService.getAllItems();

    // then
    assertThat(items).isNotNull();
    assertThat(items).hasSize(1);
    assertThat(items.stream().map(ItemTO::getCode).collect(Collectors.toList())).containsOnly(remoteItemTO.getCode());

    verify(getRequestedFor(urlMatching("/items/")));
  }

  private static String toJson(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }

}
