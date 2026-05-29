package com.evan.store.services;

import com.evan.store.entities.Order;

public interface PaymentGateway {
  CheckoutSession createCheckoutSession(Order order);
}
