package com.chris.authentication.auth.dto.course;

import com.chris.authentication.auth.enums.Level;

public class CourseUpdateDTO {

    private String title;
    private String description;
    private Level level;

    public CourseUpdateDTO(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
