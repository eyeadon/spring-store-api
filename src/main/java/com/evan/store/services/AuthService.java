package com.evan.store.services;

import com.evan.store.users.User;
import com.evan.store.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
  private final UserRepository userRepository;

  public User getCurrentUser() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    var userId = (Long) authentication.getPrincipal();

    return userRepository.findById(userId).orElse(null);
  }
}