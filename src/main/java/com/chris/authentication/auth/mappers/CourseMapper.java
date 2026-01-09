package com.chris.authentication.auth.mappers;

import com.chris.authentication.auth.dto.course.CourseDTO;
import com.chris.authentication.auth.dto.course.CourseResponseDTO;
import com.chris.authentication.auth.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CourseMapper {

    @Mapping(target = "profesor", ignore = true)
    Course courseDTOToCourse(CourseDTO dto);

    CourseResponseDTO courseToCourseResponseDTO(Course course);

}
