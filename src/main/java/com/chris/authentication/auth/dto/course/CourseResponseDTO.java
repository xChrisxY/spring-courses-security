package com.chris.authentication.auth.dto.course;

import com.chris.authentication.auth.dto.user.UserResponseDTO;
import com.chris.authentication.auth.enums.Level;

public class CourseResponseDTO {

    private String title;
    private String description;
    private Level level;
    private UserResponseDTO profesor;

    public CourseResponseDTO(){}

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

    public UserResponseDTO getProfesor() {
        return profesor;
    }

    public void setProfesor(UserResponseDTO profesor) {
        this.profesor = profesor;
    }
}
