package com.chris.authentication.auth.services.auth;

import com.chris.authentication.auth.entities.Role;
import com.chris.authentication.auth.entities.User;
import com.chris.authentication.auth.entities.UserProfile;
import com.chris.authentication.auth.repositories.RoleRepository;
import com.chris.authentication.auth.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public User register(User user){

        String passwordHash = encryptPassword(user.getPasswordHash());

        Set<Role> roles = new HashSet<>();
        Optional<Role> optionalRole = roleRepository.findByName("ROLE_STUDENT");
        optionalRole.ifPresent(roles::add);

        UserProfile profile = user.getProfile();
        profile.setUser(user);

        user.setPasswordHash(passwordHash);
        user.setProfile(profile);
        user.setRoles(roles);

        return userRepository.save(user);

    }

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
