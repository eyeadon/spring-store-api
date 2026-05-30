package com.evan.store.carts;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
  // not coupling to ProductDto
  private ProductDto product;
  private int quantity;
  private BigDecimal totalPrice;
}
