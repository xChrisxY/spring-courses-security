package com.chris.authentication.auth.repositories;

import com.chris.authentication.auth.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
