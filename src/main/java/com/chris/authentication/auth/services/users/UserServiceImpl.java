package com.chris.authentication.auth.services.users;

import com.chris.authentication.auth.entities.Role;
import com.chris.authentication.auth.entities.User;
import com.chris.authentication.auth.enums.AssignableRole;
import com.chris.authentication.auth.repositories.RoleRepository;
import com.chris.authentication.auth.repositories.UserRepository;
import com.chris.authentication.auth.security.utils.SecurityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(){

        String username = SecurityUtils.getCurrentUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("El usuario %s no fue encontrado", username)));
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con el id" + id + " no fue encontrado."));
    }

    @Override
    @Transactional
    public User updateRole(Long id, AssignableRole newRole){

        User userWithRoles = userRepository.getUserWithRoles(id)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con el id " + id + " no fue encontrado."));

        Optional<Role> optionalRole = roleRepository.findByName("ROLE_" + AssignableRole.TEACHER.toString());
        optionalRole.ifPresent(userWithRoles::addRoles);

        return userWithRoles;
    }

}
