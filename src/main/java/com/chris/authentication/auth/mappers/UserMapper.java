package com.chris.authentication.auth.mappers;

import com.chris.authentication.auth.dto.user.UserDTO;
import com.chris.authentication.auth.dto.user.UserProfileDTO;
import com.chris.authentication.auth.dto.user.UserResponseDTO;
import com.chris.authentication.auth.entities.User;
import com.chris.authentication.auth.entities.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    @Mapping(target = "passwordHash", source = "password")
    User userDTOToUser(UserDTO dto);

    UserProfileDTO userProfileToUserProfileDTO(UserProfile profile);

    @Mapping(target = "id", source = "id")
    UserResponseDTO userToUserResponseDTO(User user);

}
