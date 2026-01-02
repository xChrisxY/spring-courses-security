package com.chris.authentication.auth.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserResponseDTO {

    @NotEmpty
    @Min(0)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private UserProfileDTO userProfileDTO;

    public UserResponseDTO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfileDTO getUserProfileDTO() {
        return userProfileDTO;
    }

    public void setUserProfileDTO(UserProfileDTO userProfileDTO) {
        this.userProfileDTO = userProfileDTO;
    }
}
