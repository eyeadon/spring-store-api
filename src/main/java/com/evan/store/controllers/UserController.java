package com.evan.store.controllers;

import com.evan.store.dtos.ChangePasswordRequest;
import com.evan.store.dtos.RegisterUserRequest;
import com.evan.store.dtos.UpdateUserRequest;
import com.evan.store.dtos.UserDto;
import com.evan.store.mappers.UserMapper;
import com.evan.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

  @PostMapping
  public ResponseEntity<UserDto> createUser(
          @RequestBody RegisterUserRequest request,
          UriComponentsBuilder uriBuilder
  ) {
    var user = userMapper.toEntity(request);
    userRepository.save(user);

    var userDto = userMapper.toDto(user);
    var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();

    return ResponseEntity.created(uri).body(userDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
    var user = userRepository.findById(id).orElse(null);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }

    userMapper.update(request, user);
    userRepository.save(user);

    return ResponseEntity.ok(userMapper.toDto(user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    var user = userRepository.findById(id).orElse(null);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }

    userRepository.delete(user);

    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{id}/change-password")
  public ResponseEntity<Void> changePassword(
          @PathVariable Long id,
          @RequestBody ChangePasswordRequest request
  ) {
    var user = userRepository.findById(id).orElse(null);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }

    if (!user.getPassword().equals(request.getOldPassword())) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    user.setPassword(request.getNewPassword());
    userRepository.save(user);

    return ResponseEntity.noContent().build();
  }


}
