package com.chris.authentication.auth.repositories;

import com.chris.authentication.auth.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);

    @Query("select u from User u left join fetch u.roles where u.id = ?1")
    Optional<User> getUserWithRoles(Long id);

}
