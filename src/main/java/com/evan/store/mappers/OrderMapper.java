package com.evan.store.mappers;

import com.evan.store.dtos.OrderDto;
import com.evan.store.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
  OrderDto toDto(Order order);
}