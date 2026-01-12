package com.chris.authentication.auth.dto.enrollment;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class EnrollmentDTO {

    @NotNull
    @JsonProperty("course_id")
    private Long courseId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
