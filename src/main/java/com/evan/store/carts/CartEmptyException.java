package com.evan.store.carts;

public class CartEmptyException extends RuntimeException {
  public CartEmptyException() {
    super("Cart is empty");
  }
}
