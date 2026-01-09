package com.chris.authentication.auth.controllers;

import com.chris.authentication.auth.dto.ApiResponse;
import com.chris.authentication.auth.dto.course.CourseDTO;
import com.chris.authentication.auth.dto.course.CourseResponseDTO;
import com.chris.authentication.auth.entities.Course;
import com.chris.authentication.auth.mappers.CourseMapper;
import com.chris.authentication.auth.services.courses.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ApiResponse<CourseResponseDTO>> create(@Valid @RequestBody CourseDTO dto){

        Course newCourse = courseMapper.courseDTOToCourse(dto);
        Long profesorId = dto.getProfesorId();
        Course course = courseService.create(newCourse, profesorId);

        CourseResponseDTO courseResponseDTO = courseMapper.courseToCourseResponseDTO(course);

        ApiResponse<CourseResponseDTO> response = new ApiResponse<>(
                true,
                "Course created successfully",
                201,
                courseResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

}
