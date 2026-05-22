package com.evan.store.controllers;

import com.evan.store.dtos.OrderDto;
import com.evan.store.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  @GetMapping
  public List<OrderDto> getAllOrders() {
    return orderService.getAllOrders();
  }
}