package com.evan.store.mappers;

import com.evan.store.dtos.ProductDto;
import com.evan.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  @Mapping(source = "category.id", target = "categoryId")
  ProductDto toDto(Product product);
}
