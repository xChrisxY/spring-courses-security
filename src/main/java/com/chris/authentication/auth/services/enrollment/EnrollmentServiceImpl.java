package com.chris.authentication.auth.services.enrollment;

import com.chris.authentication.auth.dto.enrollment.EnrollmentDTO;
import com.chris.authentication.auth.dto.enrollment.EnrollmentResponseDTO;
import com.chris.authentication.auth.entities.Course;
import com.chris.authentication.auth.entities.Enrollment;
import com.chris.authentication.auth.entities.Lesson;
import com.chris.authentication.auth.entities.User;
import com.chris.authentication.auth.enums.Status;
import com.chris.authentication.auth.exceptions.courses.CourseNotFoundException;
import com.chris.authentication.auth.exceptions.enrollment.EnrollmentNotFoundException;
import com.chris.authentication.auth.exceptions.lessons.LessonNotFoundException;
import com.chris.authentication.auth.mappers.EnrollmentMapper;
import com.chris.authentication.auth.repositories.CourseRepository;
import com.chris.authentication.auth.repositories.EnrollmentRepository;
import com.chris.authentication.auth.repositories.LessonRepository;
import com.chris.authentication.auth.repositories.UserRepository;
import com.chris.authentication.auth.security.utils.SecurityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{

    private EnrollmentRepository enrollmentRepository;
    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private LessonRepository lessonRepository;
    private EnrollmentMapper enrollmentMapper;

    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            CourseRepository courseRepository,
            UserRepository userRepository,
            LessonRepository lessonRepository,
            EnrollmentMapper enrollmentMapper
    ){
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    @Override
    @Transactional
    public EnrollmentResponseDTO create(EnrollmentDTO dto){

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("El curso con el id" + dto.getCourseId() + " no fue encontrado."));

        String username = SecurityUtils.getCurrentUsername();
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con el username: " + username + " no fue encontrado."));

        Enrollment newEnrollment = enrollmentMapper.enrollmentDTOToEnrollment(dto);
        newEnrollment.setUser(student);
        newEnrollment.setCourse(course);
        newEnrollment.setProgress(0);
        newEnrollment.setStatus(Status.ACTIVE);

        Enrollment enrollment = enrollmentRepository.save(newEnrollment);

        return enrollmentMapper.enrollmentToEnrollmentResponseDTO(enrollment);

    }

    @Override
    @Transactional
    public EnrollmentResponseDTO completeLesson(Long enrollmentId, Long lessonId){

       Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
               .orElseThrow(() -> new EnrollmentNotFoundException("La suscripción con el id " + enrollmentId + " no fue encontrada."));

       Lesson lesson = lessonRepository.findById(lessonId)
               .orElseThrow(() -> new LessonNotFoundException("La lección con el id " + lessonId + " no fue encontrada."));

       if (!lesson.getCourse().getId().equals(enrollment.getCourse().getId())){
           throw new IllegalStateException("La lección no pertence al curso.");
       }

       enrollment.completeLesson(lesson);
       enrollment.recalculateProgress();

       return enrollmentMapper.enrollmentToEnrollmentResponseDTO(enrollment);

    }
}
