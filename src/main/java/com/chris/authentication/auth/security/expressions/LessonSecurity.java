package com.chris.authentication.auth.security.expressions;

import com.chris.authentication.auth.repositories.LessonRepository;
import com.chris.authentication.auth.security.utils.SecurityUtils;
import org.springframework.stereotype.Component;

@Component("lessonSecurity")
public class LessonSecurity {

    private final LessonRepository lessonRepository;

    public LessonSecurity(LessonRepository lessonRepository){
        this.lessonRepository = lessonRepository;
    }

    public boolean isLessonOwner(Long lessonId){
        String username = SecurityUtils.getCurrentUsername();
        return lessonRepository.existsByIdAndCourseProfesorUsername(lessonId, username);
    }

}
