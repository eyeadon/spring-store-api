package com.evan.store.payments;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymentException extends RuntimeException {
  // constructor that takes a message
  public PaymentException(String message) {
    super(message);
  }
}