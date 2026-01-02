package com.chris.authentication.auth.repositories;

import com.chris.authentication.auth.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
