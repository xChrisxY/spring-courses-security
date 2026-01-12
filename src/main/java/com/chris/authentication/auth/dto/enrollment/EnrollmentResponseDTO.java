package com.chris.authentication.auth.dto.enrollment;

import com.chris.authentication.auth.dto.course.CourseUpdateDTO;
import com.chris.authentication.auth.enums.Status;

import java.time.LocalDateTime;

public class EnrollmentResponseDTO {

    private Long id;
    private LocalDateTime enrolledAt;
    private Integer progress;
    private Status status;
    private CourseUpdateDTO course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CourseUpdateDTO getCourse() {
        return course;
    }

    public void setCourse(CourseUpdateDTO course) {
        this.course = course;
    }
}
