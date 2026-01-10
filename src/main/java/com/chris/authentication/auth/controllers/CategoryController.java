package com.chris.authentication.auth.controllers;

import com.chris.authentication.auth.dto.ApiResponse;
import com.chris.authentication.auth.dto.category.CategoryDTO;
import com.chris.authentication.auth.dto.category.CategoryResponseDTO;
import com.chris.authentication.auth.services.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> create(@Valid @RequestBody CategoryDTO dto){

        CategoryResponseDTO categoryResponseDTO = categoryService.create(dto);
        ApiResponse<CategoryResponseDTO> response = new ApiResponse<>("Categoria creada correctamente", categoryResponseDTO);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> list(){

        List<CategoryResponseDTO> categoryResponseDTOS = categoryService.list();

        ApiResponse<List<CategoryResponseDTO>> response = new ApiResponse<>(
                "Categorias obtenidas correctamente",
                categoryResponseDTOS
        );

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

}
