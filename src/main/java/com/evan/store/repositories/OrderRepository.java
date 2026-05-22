package com.evan.store.repositories;

import com.evan.store.entities.Order;
import com.evan.store.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
  // eager loading
  @EntityGraph(attributePaths = "items.product")
  @Query("SELECT o FROM Order o WHERE o.customer = :customer")
  List<Order> getAllByCustomer(@Param("customer") User customer);
}
