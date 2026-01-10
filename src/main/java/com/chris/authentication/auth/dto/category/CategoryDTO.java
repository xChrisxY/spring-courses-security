package com.chris.authentication.auth.dto.category;

import com.chris.authentication.auth.entities.Category;
import jakarta.validation.constraints.NotBlank;

public class CategoryDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String slug;

    public CategoryDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
