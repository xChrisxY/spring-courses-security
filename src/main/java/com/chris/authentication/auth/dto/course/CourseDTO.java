package com.chris.authentication.auth.dto.course;

import com.chris.authentication.auth.entities.Course;
import com.chris.authentication.auth.enums.Level;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CourseDTO {

    @NotBlank
    @Column(unique = true)
    @Size(min = 3, max = 30)
    private String title;

    @NotBlank
    @Size(min = 10)
    private String description;

    @NotNull
    private Level level;

    public CourseDTO(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

}
