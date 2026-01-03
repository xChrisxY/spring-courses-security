package com.chris.authentication.auth.controllers;

import com.chris.authentication.auth.dto.ApiResponse;
import com.chris.authentication.auth.dto.user.UserDTO;
import com.chris.authentication.auth.dto.user.UserResponseDTO;
import com.chris.authentication.auth.entities.User;
import com.chris.authentication.auth.mappers.UserMapper;
import com.chris.authentication.auth.services.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserMapper userMapper;

    public AuthController(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> register(@Valid @RequestBody UserDTO dto){

        User user = userMapper.userDTOToUser(dto);
        User newUser = authService.register(user);

        UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(newUser);

        ApiResponse<UserResponseDTO> response = new ApiResponse<>(
                true,
                "User created successfully",
                201,
                userResponseDTO
        );

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);

    }



}
