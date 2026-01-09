package com.chris.authentication.auth.controllers;

import com.chris.authentication.auth.dto.ApiResponse;
import com.chris.authentication.auth.dto.user.UpdateRoleDTO;
import com.chris.authentication.auth.dto.user.UserResponseDTO;
import com.chris.authentication.auth.entities.User;
import com.chris.authentication.auth.mappers.UserMapper;
import com.chris.authentication.auth.services.users.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getProfile(){

        User user = userService.findByUsername();
        UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(user);

        ApiResponse<UserResponseDTO> response = new ApiResponse<>(
                true,
                "Usuario obtenido satisfactoriamente",
                200,
                userResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(@PathVariable Long id){

        User user = userService.findById(id);
        UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(user);

        ApiResponse<UserResponseDTO> response = new ApiResponse<>(
                true,
                "Usuario obtenido satisfactoriamente",
                200,
                userResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateRoles(@PathVariable Long id, @Valid @RequestBody UpdateRoleDTO dto){

        User user = userService.updateRole(id, dto.getRole());
        UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(user);

        ApiResponse<UserResponseDTO> response = new ApiResponse<>(
                true,
                "Rol añadido satisfactoriamente",
                200,
                userResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

}
