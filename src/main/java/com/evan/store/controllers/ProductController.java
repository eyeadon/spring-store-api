package com.evan.store.controllers;

import com.evan.store.dtos.ProductDto;
import com.evan.store.dtos.UserDto;
import com.evan.store.mappers.ProductMapper;
import com.evan.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @GetMapping
  public Iterable<ProductDto> getAllProducts(
          @RequestParam(required = false, defaultValue = "", name = "categoryId")
          Byte categoryId) {

    if (categoryId == null || categoryId.toString().isEmpty()) {
      return productRepository.findAll()
              .stream()
              // .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
              .map(productMapper::toDto)
              .toList();
    }

    return productRepository.findAll(Sort.by("categoryId"))
            .stream()
            // .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
            .map(productMapper::toDto)
            .toList();
  }


}
