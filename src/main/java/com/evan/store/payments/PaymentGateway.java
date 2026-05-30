package com.evan.store.payments;

import com.evan.store.orders.Order;

import java.util.Optional;

public interface PaymentGateway {
  CheckoutSession createCheckoutSession(Order order);
  Optional<PaymentResult> parseWebhookRequest(WebhookRequest request);

}
