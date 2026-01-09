package com.chris.authentication.auth.services.courses;

import com.chris.authentication.auth.entities.Course;
import com.chris.authentication.auth.entities.User;
import com.chris.authentication.auth.enums.AssignableRole;
import com.chris.authentication.auth.exceptions.user.UserNotFoundException;
import com.chris.authentication.auth.repositories.CourseRepository;
import com.chris.authentication.auth.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;
    private UserRepository userRepository;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository){
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Course create(Course course, Long profesorId){

        User profesor = userRepository.findById(profesorId)
                .orElseThrow(() -> new UserNotFoundException("Profesor no encontrado con el ID " + profesorId));

        course.setProfesor(profesor);
        return courseRepository.save(course);

    }



}
