package com.chris.authentication.auth.mappers;

import com.chris.authentication.auth.dto.role.RoleDTO;
import com.chris.authentication.auth.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role roleDTOtoRole(RoleDTO dto);

}
