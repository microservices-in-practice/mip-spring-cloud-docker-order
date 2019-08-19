package com.capgemini.mip.order.client.catalog;

import java.util.List;

public interface CatalogClient {

  public List<RemoteItemTO> getAllItems();

  public RemoteItemTO getItemByCode(String code);

}
