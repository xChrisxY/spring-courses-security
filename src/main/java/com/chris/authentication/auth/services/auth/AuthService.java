package com.chris.authentication.auth.services.auth;

import com.chris.authentication.auth.entities.User;

public interface AuthService {

    User register(User user);
    String encryptPassword(String password);
}
