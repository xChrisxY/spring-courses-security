package com.chris.authentication.auth.mappers;

import com.chris.authentication.auth.dto.enrollment.EnrollmentDTO;
import com.chris.authentication.auth.dto.enrollment.EnrollmentResponseDTO;
import com.chris.authentication.auth.entities.Enrollment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    Enrollment enrollmentDTOToEnrollment(EnrollmentDTO dto);
    EnrollmentResponseDTO enrollmentToEnrollmentResponseDTO(Enrollment enrollment);

}
