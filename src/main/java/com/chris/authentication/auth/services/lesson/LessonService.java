package com.chris.authentication.auth.services.lesson;

import com.chris.authentication.auth.dto.lesson.LessonDTO;
import com.chris.authentication.auth.dto.lesson.LessonResponseDTO;

import java.util.List;

public interface LessonService {
    LessonResponseDTO create(Long courseId, LessonDTO lessonDTO);
    List<LessonResponseDTO> list(Long courseId);
    LessonResponseDTO update(Long lessonId, LessonDTO lessonDTO);
    void delete(Long lessonId);
}
