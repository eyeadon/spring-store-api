package com.evan.store.controllers;

import com.evan.store.dtos.UserDto;
import com.evan.store.entities.User;
import com.evan.store.mappers.UserMapper;
import com.evan.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @GetMapping
  public Iterable<UserDto> getAllUsers() {
    return userRepository.findAll()
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
    // return new ResponseEntity<>(user, HttpStatus.OK);
    return ResponseEntity.ok(userMapper.toDto(user));
  }

}
