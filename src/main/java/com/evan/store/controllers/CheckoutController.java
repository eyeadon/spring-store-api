package com.evan.store.controllers;

import com.evan.store.dtos.CheckoutRequest;
import com.evan.store.dtos.CheckoutResponse;
import com.evan.store.dtos.ErrorDto;
import com.evan.store.exceptions.CartEmptyException;
import com.evan.store.exceptions.CartNotFoundException;
import com.evan.store.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
  private final CheckoutService checkoutService;

  @PostMapping
  public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
    return checkoutService.checkout(request);
  }

  @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
  public ResponseEntity<ErrorDto> handleException(Exception ex) {
    return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
  }

}