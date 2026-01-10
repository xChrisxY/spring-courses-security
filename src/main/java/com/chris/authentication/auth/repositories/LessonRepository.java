package com.chris.authentication.auth.repositories;

import com.chris.authentication.auth.entities.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    List<Lesson> findLessonsByCourseId(Long courseId);
    boolean existsByIdAndCourseProfesorUsername(Long lessonId, String username);
}
