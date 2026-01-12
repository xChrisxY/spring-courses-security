package com.chris.authentication.auth.services.enrollment;

import com.chris.authentication.auth.dto.enrollment.EnrollmentDTO;
import com.chris.authentication.auth.dto.enrollment.EnrollmentResponseDTO;
import com.chris.authentication.auth.entities.Enrollment;

public interface EnrollmentService {
    EnrollmentResponseDTO create(EnrollmentDTO dto);
    EnrollmentResponseDTO completeLesson(Long enrollmentId, Long lessonId);
}
