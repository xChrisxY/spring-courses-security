package com.chris.authentication.auth.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserProfileDTO {

    @JsonProperty("first_name")
    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;

    @NotNull
    @Min(18)
    private Integer age;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @NotBlank
    @Size(min = 10, max = 50)
    private String bio;

    public UserProfileDTO(){}

}
