package com.chris.authentication.auth.security.expressions;

import com.chris.authentication.auth.repositories.CourseRepository;
import com.chris.authentication.auth.security.utils.SecurityUtils;
import org.springframework.stereotype.Component;

@Component("courseSecurity")
public class CourseSecurity {

    private final CourseRepository courseRepository;

    public CourseSecurity(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public boolean isOwner(Long courseId){
        String username = SecurityUtils.getCurrentUsername();
        return courseRepository.existsByIdAndProfesorUsername(courseId, username);

    }

}
