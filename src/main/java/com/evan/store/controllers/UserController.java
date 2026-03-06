package com.evan.store.controllers;

import com.evan.store.dtos.UserDto;
import com.evan.store.mappers.UserMapper;
import com.evan.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @GetMapping
  public Iterable<UserDto> getAllUsers(
          @RequestParam(required = false, defaultValue = "", name = "sort")
          String sort) {
    if (!Set.of("name", "email").contains(sort)) {
      sort = "name";
    }

    return userRepository.findAll(Sort.by(sort))
            .stream()
            // .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
            .map(userMapper::toDto)
            .toList();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
    var user = userRepository.findById(id).orElse(null);
    if (user == null) {
      // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      return ResponseEntity.notFound().build();
    }

    // var userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
    // return new ResponseEntity<>(userDto, HttpStatus.OK);
    return ResponseEntity.ok(userMapper.toDto(user));
  }

}
