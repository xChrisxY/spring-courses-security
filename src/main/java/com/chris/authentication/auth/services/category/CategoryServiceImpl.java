package com.chris.authentication.auth.services.category;

import com.chris.authentication.auth.dto.category.CategoryDTO;
import com.chris.authentication.auth.dto.category.CategoryResponseDTO;
import com.chris.authentication.auth.entities.Category;
import com.chris.authentication.auth.mappers.CategoryMapper;
import com.chris.authentication.auth.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponseDTO create(CategoryDTO category){

        Category newCategory = categoryMapper.categoryDTOToCategory(category);
        Category savedCategory = categoryRepository.save(newCategory);
        return categoryMapper.categoryToCategoryResponseDTO(savedCategory);

    }

    @Override
    public List<CategoryResponseDTO> list(){

        List<Category> categories = (List<Category>) categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();

        categories.forEach(category -> {
            categoryResponseDTOS.add(categoryMapper.categoryToCategoryResponseDTO(category));
        });

        return categoryResponseDTOS;
    }
}
