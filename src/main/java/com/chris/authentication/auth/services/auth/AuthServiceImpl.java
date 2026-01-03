package com.chris.authentication.auth.services.auth;

import com.chris.authentication.auth.entities.User;
import com.chris.authentication.auth.entities.UserProfile;
import com.chris.authentication.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user){

        String passwordHash = this.encryptPassword(user.getPasswordHash());
        user.setPasswordHash(passwordHash);

        UserProfile profile = user.getProfile();
        profile.setUser(user);
        user.setProfile(profile);

        return userRepository.save(user);

    }

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
