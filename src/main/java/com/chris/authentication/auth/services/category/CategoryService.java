package com.chris.authentication.auth.services.category;

import com.chris.authentication.auth.dto.category.CategoryDTO;
import com.chris.authentication.auth.dto.category.CategoryResponseDTO;

import java.util.List;

public interface CategoryService{
    CategoryResponseDTO create(CategoryDTO category);
    List<CategoryResponseDTO> list();
}
