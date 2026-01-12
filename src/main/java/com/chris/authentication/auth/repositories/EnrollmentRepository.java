package com.chris.authentication.auth.repositories;

import com.chris.authentication.auth.entities.Enrollment;
import org.springframework.data.repository.CrudRepository;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
}
