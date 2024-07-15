package com.thanhtuyen.webbook.mapper;

import com.thanhtuyen.webbook.dto.UserDTO;
import com.thanhtuyen.webbook.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserDTO toDTO(User user);
    public User toEntity(UserDTO userDTO);
}
