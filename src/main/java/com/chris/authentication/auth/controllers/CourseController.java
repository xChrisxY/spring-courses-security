package com.chris.authentication.auth.controllers;

import com.chris.authentication.auth.dto.ApiResponse;
import com.chris.authentication.auth.dto.course.CourseDTO;
import com.chris.authentication.auth.dto.course.CourseResponseDTO;
import com.chris.authentication.auth.dto.course.CourseUpdateDTO;
import com.chris.authentication.auth.entities.Course;
import com.chris.authentication.auth.mappers.CourseMapper;
import com.chris.authentication.auth.services.courses.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private CourseService courseService;
    private CourseMapper courseMapper;

    public CourseController(CourseService courseService, CourseMapper courseMapper){
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ApiResponse<CourseResponseDTO>> create(@Valid @RequestBody CourseDTO dto){

        Course newCourse = courseMapper.courseDTOToCourse(dto);
        Course course = courseService.create(newCourse);

        CourseResponseDTO courseResponseDTO = courseMapper.courseToCourseResponseDTO(course);

        ApiResponse<CourseResponseDTO> response = new ApiResponse<>(
                true,
                "Course created successfully",
                201,
                courseResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponseDTO>>> list(){

        List<Course> courses = courseService.list();
        List<CourseResponseDTO> courseResponseDTOS = new ArrayList<>();

        courses.forEach(course -> {
            courseResponseDTOS.add(courseMapper.courseToCourseResponseDTO(course));
        });

        ApiResponse<List<CourseResponseDTO>> response = new ApiResponse<>(
                true,
                "Courses retreived successfully",
                200,
                courseResponseDTOS
        );

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponseDTO>> getById(@PathVariable Long id){

        Course course = courseService.getById(id);
        CourseResponseDTO courseResponseDTO = courseMapper.courseToCourseResponseDTO(course);

        ApiResponse<CourseResponseDTO> response = new ApiResponse<>(
                true,
                "Course retrieved successfully",
                200,
                courseResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseUpdateDTO>> update(@PathVariable Long id, @Valid @RequestBody CourseDTO dto){

        Course courseData = courseMapper.courseDTOToCourse(dto);
        Course existingCourse = courseService.update(id, courseData);
        CourseUpdateDTO courseResponseDTO = courseMapper.courseToCourseUpdateDTO(existingCourse);

        ApiResponse<CourseUpdateDTO> response = new ApiResponse<>(
                true,
                "Course updated successfully",
                200,
                courseResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> delete(@PathVariable Long courseId){

        courseService.delete(courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();

    }


}
