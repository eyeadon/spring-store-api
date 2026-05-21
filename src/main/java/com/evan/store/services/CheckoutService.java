package com.evan.store.services;

import com.evan.store.dtos.CheckoutRequest;
import com.evan.store.dtos.CheckoutResponse;
import com.evan.store.entities.Order;
import com.evan.store.exceptions.CartEmptyException;
import com.evan.store.exceptions.CartNotFoundException;
import com.evan.store.repositories.CartRepository;
import com.evan.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CheckoutService {
  private final CartRepository cartRepository;
  private final OrderRepository orderRepository;
  private final AuthService authService;
  private final CartService cartService;

  public CheckoutResponse checkout(CheckoutRequest request) {
    var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
    if (cart == null) {
      throw new CartNotFoundException();
    }

    if (cart.isEmpty()) {
      throw new CartEmptyException();
    }

    var order = Order.fromCart(cart, authService.getCurrentUser());

    orderRepository.save(order);

    cartService.clearCart(cart.getId());

    return new CheckoutResponse(order.getId());
  }
}