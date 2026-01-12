package com.chris.authentication.auth.services.lesson;

import com.chris.authentication.auth.dto.lesson.LessonDTO;
import com.chris.authentication.auth.dto.lesson.LessonResponseDTO;
import com.chris.authentication.auth.entities.Course;
import com.chris.authentication.auth.entities.Lesson;
import com.chris.authentication.auth.exceptions.courses.CourseNotFoundException;
import com.chris.authentication.auth.exceptions.lessons.LessonNotFoundException;
import com.chris.authentication.auth.mappers.LessonMapper;
import com.chris.authentication.auth.repositories.CourseRepository;
import com.chris.authentication.auth.repositories.LessonRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private LessonRepository lessonRepository;
    private CourseRepository courseRepository;
    private LessonMapper lessonMapper;

    public LessonServiceImpl(
            LessonRepository lessonRepository,
            CourseRepository courseRepository,
            LessonMapper lessonMapper
    ){
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.lessonMapper = lessonMapper;
    }

    @Override
    @Transactional
    @PreAuthorize("@courseSecurity.isOwner(#courseId)")
    public LessonResponseDTO create(Long courseId, LessonDTO lessonDTO){

        Lesson newLesson = lessonMapper.lessonDTOToLesson(lessonDTO);

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Curso no encontrado: " + courseId));

        newLesson.setCourse(course);
        Lesson lesson = lessonRepository.save(newLesson);

        return lessonMapper.lessonToLessonResponseDTO(newLesson);

    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonResponseDTO> list(Long courseId){

        List<Lesson> lessons = lessonRepository.findLessonsByCourseId(courseId);
        List<LessonResponseDTO> lessonResponseDTOS = new ArrayList<>();

        lessons.forEach(lesson -> {
            lessonResponseDTOS.add(lessonMapper.lessonToLessonResponseDTO(lesson));
        });

        return lessonResponseDTOS;
    }

    @Override
    @Transactional
    @PreAuthorize("@lessonSecurity.isLessonOwner(#lessonId)")
    public LessonResponseDTO update(Long lessonId, LessonDTO lessonDTO){

        Lesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException("La lección no fue encontrada: " + lessonId));

        lessonMapper.updateLessonToLessonDTO(lessonDTO, existingLesson);

        Lesson savedLesson = lessonRepository.save(existingLesson);
        return lessonMapper.lessonToLessonResponseDTO(existingLesson);
    }

    @Override
    @Transactional
    @PreAuthorize("@lessonSecurity.isLessonOwner(#lessonId)")
    public void delete(Long lessonId){

        Lesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException("La lección no fue encontrada: " + lessonId));

        lessonRepository.delete(existingLesson);
    }
}
