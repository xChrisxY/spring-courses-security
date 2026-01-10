package com.chris.authentication.auth.repositories;

import com.chris.authentication.auth.entities.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
