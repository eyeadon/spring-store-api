package com.evan.store.mappers;

import com.evan.store.dtos.CartDto;
import com.evan.store.dtos.CartItemDto;
import com.evan.store.entities.Cart;
import com.evan.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
  // @Mapping(target = "items", source = "items")
  @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
  CartDto toDto(Cart cart);

  @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
  CartItemDto toDto(CartItem cartItem);
}
