package com.evan.store.mappers;

import com.evan.store.dtos.UserDto;
import com.evan.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toDto(User user);
}
