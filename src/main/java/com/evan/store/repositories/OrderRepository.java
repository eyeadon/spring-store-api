package com.evan.store.repositories;

import com.evan.store.entities.Order;
import com.evan.store.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
  // eager loading
  @EntityGraph(attributePaths = "items.product")
  @Query("SELECT o FROM Order o WHERE o.customer = :customer")
  List<Order> getOrdersByCustomer(@Param("customer") User customer);

  @EntityGraph(attributePaths = "items.product")
  @Query("SELECT o FROM Order o WHERE o.id = :orderId")
  Optional<Order> getOrderWithItems(@Param("orderId") Long orderId);

}
