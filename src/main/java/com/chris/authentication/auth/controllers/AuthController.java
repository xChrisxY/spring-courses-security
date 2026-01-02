package com.chris.authentication.auth.controllers;

import com.chris.authentication.auth.dto.user.UserDTO;
import com.chris.authentication.auth.entities.User;
import com.chris.authentication.auth.mappers.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserMapper userMapper;

    public AuthController(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO dto){

        User user = userMapper.userDTOToUser(dto);
        System.out.println(user);

        return ResponseEntity.ok().build();

    }



}
