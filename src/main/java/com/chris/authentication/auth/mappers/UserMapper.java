package com.chris.authentication.auth.mappers;

import com.chris.authentication.auth.dto.user.UserDTO;
import com.chris.authentication.auth.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "passwordHash", source = "password")
    User userDTOToUser(UserDTO dto);

}
