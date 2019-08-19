package com.capgemini.mip.order.repository;

import com.capgemini.mip.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("select o from Order o where o.customer.code = :code")
  List<Order> findByCustomerCode(@Param("code") String code);

  Order findByOrderId(String orderId);
}
