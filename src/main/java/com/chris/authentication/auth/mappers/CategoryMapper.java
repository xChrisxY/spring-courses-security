package com.chris.authentication.auth.mappers;

import com.chris.authentication.auth.dto.category.CategoryDTO;
import com.chris.authentication.auth.dto.category.CategoryResponseDTO;
import com.chris.authentication.auth.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category categoryDTOToCategory(CategoryDTO dto);
    CategoryResponseDTO categoryToCategoryResponseDTO(Category category);

}
