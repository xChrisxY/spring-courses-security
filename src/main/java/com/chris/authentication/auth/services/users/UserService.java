package com.chris.authentication.auth.services.users;

import com.chris.authentication.auth.entities.User;
import com.chris.authentication.auth.enums.AssignableRole;

public interface UserService {

    boolean existsByUsername(String username);
    User findByUsername();
    User findById(Long id);
    User updateRole(Long id, AssignableRole role);
}
