package com.chris.authentication.auth.controllers;

import com.chris.authentication.auth.dto.ApiResponse;
import com.chris.authentication.auth.dto.enrollment.EnrollmentDTO;
import com.chris.authentication.auth.dto.enrollment.EnrollmentResponseDTO;
import com.chris.authentication.auth.services.enrollment.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService){
        this.enrollmentService = enrollmentService;
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping
    public ResponseEntity<ApiResponse<EnrollmentResponseDTO>> create(@Valid @RequestBody EnrollmentDTO dto){

        EnrollmentResponseDTO enrollmentResponseDTO = enrollmentService.create(dto);
        ApiResponse<EnrollmentResponseDTO> response = new ApiResponse<>("Enrollment created successfully", enrollmentResponseDTO);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);

    }

    @PostMapping("/{enrollmentId}/lessons/{lessonId}/complete")
    public ResponseEntity<ApiResponse<EnrollmentResponseDTO>> completeLesson(@PathVariable Long enrollmentId, @PathVariable Long lessonId){

        EnrollmentResponseDTO enrollmentResponseDTO = enrollmentService.completeLesson(enrollmentId, lessonId);
        ApiResponse<EnrollmentResponseDTO> response = new ApiResponse<>("Enrollment created successfully", enrollmentResponseDTO);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
    }

}
