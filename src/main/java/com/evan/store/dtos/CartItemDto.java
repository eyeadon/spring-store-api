package com.evan.store.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
  // not coupling to ProductDto
  private CartProductDto product;
  private int quantity;
  private BigDecimal totalPrice;
}
