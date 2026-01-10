package com.chris.authentication.auth.controllers;

import com.chris.authentication.auth.dto.ApiResponse;
import com.chris.authentication.auth.dto.lesson.LessonDTO;
import com.chris.authentication.auth.dto.lesson.LessonResponseDTO;
import com.chris.authentication.auth.entities.Lesson;
import com.chris.authentication.auth.services.lesson.LessonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lessons")
public class LessonController {

    private LessonService lessonService;

    public LessonController(LessonService lessonService){
        this.lessonService = lessonService;
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping("/{lessonId}")
    public ResponseEntity<ApiResponse<LessonResponseDTO>> update(@PathVariable Long lessonId, @Valid @RequestBody LessonDTO dto){
        LessonResponseDTO lessonResponseDTO = lessonService.update(lessonId, dto);
        ApiResponse<LessonResponseDTO> response = new ApiResponse<>(
        "lesson updated successfully",
                lessonResponseDTO
        );
        return ResponseEntity.status(HttpStatus.OK.value()).body(response);

    }

    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> delete(@PathVariable Long lessonId){

        lessonService.delete(lessonId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();

    }

}
