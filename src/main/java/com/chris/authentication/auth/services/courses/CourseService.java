package com.chris.authentication.auth.services.courses;

import com.chris.authentication.auth.entities.Course;
import com.chris.authentication.auth.entities.User;

public interface CourseService {
    Course create(Course course, Long profesorId);
}
