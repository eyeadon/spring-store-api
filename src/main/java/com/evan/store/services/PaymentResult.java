package com.evan.store.services;

import com.evan.store.entities.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentResult {
  private Long orderId;
  private PaymentStatus paymentStatus;
}