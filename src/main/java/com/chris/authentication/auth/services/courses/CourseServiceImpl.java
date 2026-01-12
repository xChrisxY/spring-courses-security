package com.chris.authentication.auth.services.courses;

import com.chris.authentication.auth.entities.Course;
import com.chris.authentication.auth.entities.User;
import com.chris.authentication.auth.exceptions.courses.CourseNotFoundException;
import com.chris.authentication.auth.exceptions.user.NotTeacherPermissions;
import com.chris.authentication.auth.mappers.CourseMapper;
import com.chris.authentication.auth.repositories.CourseRepository;
import com.chris.authentication.auth.repositories.UserRepository;
import com.chris.authentication.auth.security.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private CourseMapper courseMapper;

    public CourseServiceImpl(
            CourseRepository courseRepository,
            UserRepository userRepository,
            CourseMapper courseMapper
    ){
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public Course create(Course course){

        String username = SecurityUtils.getCurrentUsername();
        User profesor = userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado. "));

        course.setProfesor(profesor);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> list(){
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course getById(Long courseId){

        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(String.format("Curso con el %s no fue encontrado", courseId)));

    }

    @PreAuthorize("@courseSecurity.isOwner(#courseId)")
    @Override
    public Course update(Long courseId, Course course){

       Course existingCourse = getById(courseId);
       courseMapper.updateCourseFromDTO(course, existingCourse);
       return courseRepository.save(existingCourse);

    }

    @PreAuthorize("hasRole('ADMIN') or @courseSecurity.isOwner(#courseId)")
    @Override
    public void delete(Long courseId){

        Course course = getById(courseId);
        courseRepository.delete(course);

    }
}
