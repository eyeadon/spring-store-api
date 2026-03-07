package com.evan.store.mappers;

import com.evan.store.dtos.RegisterUserRequest;
import com.evan.store.dtos.UpdateUserRequest;
import com.evan.store.dtos.UserDto;
import com.evan.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toDto(User user);
  User toEntity(RegisterUserRequest request);
  void update(UpdateUserRequest request, @MappingTarget User user);
}
