package com.evan.store.controllers;

import com.evan.store.dtos.ProductDto;
import com.evan.store.entities.Product;
import com.evan.store.mappers.ProductMapper;
import com.evan.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @GetMapping
  public List<ProductDto> getAllProducts(
          @RequestParam(required = false, name = "categoryId")
          Byte categoryId) {
    List<Product> products;

    if (categoryId != null ) {
      products = productRepository.findByCategoryId(categoryId);
    } else {
      products = productRepository.findAllWithCategory();
    }

    return products
            .stream()
            // .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
            .map(productMapper::toDto)
            .toList();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
    var product = productRepository.findById(id).orElse(null);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(productMapper.toDto(product));
  }



}
