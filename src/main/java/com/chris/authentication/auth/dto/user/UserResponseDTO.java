package com.chris.authentication.auth.dto.user;

import com.chris.authentication.auth.entities.Role;

import java.util.List;

public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private UserProfileDTO profile;
    private List<Role> roles;

    public UserResponseDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UserProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(UserProfileDTO profile) {
        this.profile = profile;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
