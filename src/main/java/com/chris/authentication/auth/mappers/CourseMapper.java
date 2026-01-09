package com.chris.authentication.auth.mappers;

import com.chris.authentication.auth.dto.course.CourseDTO;
import com.chris.authentication.auth.dto.course.CourseResponseDTO;
import com.chris.authentication.auth.dto.course.CourseUpdateDTO;
import com.chris.authentication.auth.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CourseMapper {

    Course courseDTOToCourse(CourseDTO dto);
    CourseResponseDTO courseToCourseResponseDTO(Course course);
    CourseUpdateDTO courseToCourseUpdateDTO(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "profesor", ignore = true)
    void updateCourseFromDTO(Course source, @MappingTarget Course target);

}
