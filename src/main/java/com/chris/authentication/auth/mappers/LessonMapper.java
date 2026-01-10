package com.chris.authentication.auth.mappers;

import com.chris.authentication.auth.dto.lesson.LessonDTO;
import com.chris.authentication.auth.dto.lesson.LessonResponseDTO;
import com.chris.authentication.auth.entities.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    Lesson lessonDTOToLesson(LessonDTO dto);
    LessonResponseDTO lessonToLessonResponseDTO(Lesson lesson);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    void updateLessonToLessonDTO(LessonDTO source, @MappingTarget Lesson target);

}
