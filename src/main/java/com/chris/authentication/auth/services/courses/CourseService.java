package com.chris.authentication.auth.services.courses;

import com.chris.authentication.auth.entities.Course;

import java.util.List;

public interface CourseService {
    Course create(Course course);
    List<Course> list();
    Course getById(Long courseId);
    Course update(Long courseId, Course course);
    void delete(Long courseId);
}
