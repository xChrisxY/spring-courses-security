package com.chris.authentication.auth.dto.user;

import com.chris.authentication.auth.enums.AssignableRole;
import jakarta.validation.constraints.NotNull;

public class UpdateRoleDTO {

    @NotNull
    private AssignableRole role;

    public AssignableRole getRole() {
        return role;
    }

    public void setRole(AssignableRole role) {
        this.role = role;
    }
}
